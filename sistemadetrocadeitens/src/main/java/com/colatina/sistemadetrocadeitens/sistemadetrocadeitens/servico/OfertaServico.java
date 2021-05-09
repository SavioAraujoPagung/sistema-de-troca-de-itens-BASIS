package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Situacao;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.OfertaRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.EmailDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.exception.RegraNegocioException;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.OfertaMepper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfertaServico {

    private final OfertaRepositorio ofertaRepositorio;
    private final OfertaMepper ofertaMepper;
    private final ItemServico itemServico;
    private final UsuarioServico usuarioServico;
    private final EmailServico emailServico;

    private final Long ABERTA = 1L;
    private final Long ACEITAR = 2L;
    private final Long CANCELAR = 3L;
    private final Long RECUSAR = 4L;

    private void enviarEmail(EmailDto emailDto) { emailServico.sendMail(emailDto); }

    public OfertaDto obterPorId(Long id){
        Oferta oferta = ofertaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        return ofertaMepper.toDto(oferta);
    }

    public List<OfertaDto> listar(){
        List<Oferta> oferta = ofertaRepositorio.findAll();
        return ofertaMepper.toDto(oferta);
    }
    public OfertaDto alterar(OfertaDto ofertaDto){
        obterPorId(ofertaDto.getId());
        return OfertaDtoSave(ofertaDto);
    }

    public OfertaDto salvar(OfertaDto ofertaDto){
        validarDonoDoItemDisponivel(ofertaDto);
        validarDonoDosItensOfertados(ofertaDto);
        ofertaDto.setSituacaoId(ABERTA);
        enviarEmailNovaOferta(ofertaDto);
        return OfertaDtoSave(ofertaDto);
    }

    public void deletar(Long id){
        Oferta oferta = ofertaMepper.toEntity(obterPorId(id));
        ofertaRepositorio.delete(oferta);
    }

    public OfertaDto mudarSituacao(Long id, Long novaSituacao){
        OfertaDto ofertaDto = obterPorId(id);
        if (ACEITAR.equals(novaSituacao)){
            trocarItemOfertado(ofertaDto);
            trocarItemDisponivel(ofertaDto);
            cancelarOfertasComItem(ofertaDto.getItemId());
            ofertaDto.getItensOfertados().forEach( itemOfertado -> { cancelarOfertasComItem(itemOfertado); } );
        }
        ofertaDto.setSituacaoId(novaSituacao);
        ofertaDto = alterar(ofertaDto);
        return ofertaDto;
    }

    private void validarDonoDosItensOfertados(OfertaDto ofertaDto){
        ofertaDto.getItensOfertados().forEach(itemOfertado -> {
            UsuarioDto usuarioDto = itemServico.obterDono(itemOfertado);
            if (!ofertaDto.getUsuarioOfertanteId().equals(usuarioDto.getId())){
                throw new RegraNegocioException("Um ou mais dos itens ofertados não pertencem ao usuario ofertante");
            }
        });
    }

    private void validarDonoDoItemDisponivel(OfertaDto ofertaDto){
        UsuarioDto usuarioDto = itemServico.obterDono(ofertaDto.getItemId());
        if (ofertaDto.getUsuarioOfertanteId().equals(usuarioDto.getId())){
            throw new RegraNegocioException("Um usuario não pode propor uma troca por um item que já o pertence");
        }
    }

    private OfertaDto OfertaDtoSave(OfertaDto ofertaDto){
        Oferta oferta = ofertaMepper.toEntity(ofertaDto);
        ofertaRepositorio.save(oferta);
        return ofertaMepper.toDto(oferta);
    }

    private void trocarItemOfertado(OfertaDto ofertaDto){
        UsuarioDto usuarioDto = itemServico.obterDono(ofertaDto.getItemId());
        List<ItemDto> itensDto = new ArrayList<ItemDto>();
        ofertaDto.getItensOfertados().forEach(itemOfertado -> {
            ItemDto dto = itemServico.obterPorId(itemOfertado);
            dto.setDisponibilidade(false);
            dto.setUsuarioId(usuarioDto.getId());
            itensDto.add(dto);
        });
        itemServico.salvarVarios(itensDto);

    }

    public List<OfertaDto> salvarVarios(List<OfertaDto> ofertaDtos){
        List<Oferta> ofertas = ofertaMepper.toEntity(ofertaDtos);
        ofertaRepositorio.saveAll(ofertas);
        return ofertaMepper.toDto(ofertas);
    }

    private void trocarItemDisponivel(OfertaDto ofertaDto){
        ItemDto itemDto = itemServico.obterPorId(ofertaDto.getItemId());
        itemDto.setDisponibilidade(false);
        itemDto.setUsuarioId(ofertaDto.getUsuarioOfertanteId());
        itemServico.alterar(itemDto);
    }

    private void cancelarOfertasComItem(Long idItem){
        List<OfertaDto> ofertaDtos = ofertaMepper.toDto(ofertaRepositorio.findAllBySituacao_Id(ABERTA));
        List<OfertaDto> ofertaDtosCanceladas = new ArrayList<OfertaDto>();
        ofertaDtos.forEach(oferta -> {
            if ( idItem.equals(oferta.getItemId()) || oferta.getItensOfertados().contains(idItem) ){
                oferta.setSituacaoId(CANCELAR);
                ofertaDtosCanceladas.add(oferta);
            }
        });
        if (ofertaDtosCanceladas.isEmpty()){ salvarVarios(ofertaDtosCanceladas); }
    }

    private void enviarEmailNovaOferta(OfertaDto ofertaDto){
        UsuarioDto usuarioDtoDisponivel = itemServico.obterDono(ofertaDto.getItemId());
        UsuarioDto usuarioDtoOfertante = usuarioServico.obterPorId(ofertaDto.getUsuarioOfertanteId());

        EmailDto emailDto = new EmailDto();
        emailDto.setAssunto("UM DE SEUS PRODUTOS RECEBEU UMA NOVA OFERTA!");
        emailDto.setDestinatario(usuarioDtoDisponivel.getEmail());


        emailDto.setTexto("O senhor(a) " + usuarioDtoOfertante.getNome() +
                " ofereceu "+ ofertaDto.getItensOfertados().size() +
                " produto(s) pelo seu produto: " + itemServico.obterPorId(ofertaDto.getItemId()).getNome());

        enviarEmail(emailDto);
    }
}
