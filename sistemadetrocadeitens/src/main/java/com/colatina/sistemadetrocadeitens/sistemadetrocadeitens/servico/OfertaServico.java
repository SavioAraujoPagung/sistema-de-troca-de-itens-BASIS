package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Situacao;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.OfertaRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.SituacaoRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.EmailDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.exception.RegraNegocioException;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.ItemMapper;
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
    private final EmailServico emailServico;
    private final UsuarioServico usuarioServico;
    private final ItemServico itemServico;
    private final ItemMapper itemMapper;
    private final Long IDCANCELADA = 3L;
    private final Long IDACEITAR = 2L;
    private final SituacaoRepositorio situacaoRepositorio;

    private void enviarEmail(EmailDto emailDto) {
        emailServico.sendMail(emailDto);
    }

    public OfertaDto aceitar(OfertaDto ofertaAceitaDto){
        Oferta ofertaAceita = ofertaRepositorio.findById(ofertaAceitaDto.getId()).orElseThrow(() -> new RegraNegocioException("Oferta " +
                "não encontrada"));
        Situacao cancelada = situacaoRepositorio.findById(IDACEITAR).orElseThrow(() -> new RegraNegocioException(""));
        ofertaAceita.setSituacao(cancelada);
        Oferta oferta = ofertaMepper.toEntity(ofertaAceitaDto);

        Situacao concluida = situacaoRepositorio.getOne(IDACEITAR);
        oferta.setSituacao(concluida);
        ofertaRepositorio.save(oferta);

        List<OfertaDto> ofertasDtoCanceladas = listar();
        List<Oferta> ofertasCanceladas = new ArrayList<>();
        for (OfertaDto oft: ofertasDtoCanceladas) {
//            if (ofertaAceita.getItemId().equals(oft.getItemId()) &&oferta.getId().equals(ofertasCanceladas)){
//                Oferta ofertaCancelada = ofertaMepper.toEntity(oft);
//                ofertaCancelada.setSituacao(cancelada);
//                ofertasCanceladas.add(ofertaCancelada);
//            }
        }
        ofertaRepositorio.saveAll(ofertasCanceladas);
//
        return ofertaAceitaDto;
    }

    public OfertaDto obter (Long id){
        Oferta oferta = ofertaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Oferta " +
                "não encontrada"));
        return ofertaMepper.toDto(oferta);
    }

    public List<OfertaDto> listar(){
        List<Oferta> ofertas = ofertaRepositorio.findAll();
        List<OfertaDto> ofertaDtos = new ArrayList<>();
        for (Oferta oferta: ofertas) {
            ofertaDtos.add(ofertaMepper.toDto(oferta));
        }
        return ofertaDtos;
    }
    public OfertaDto alterar(OfertaDto ofertaDto){
        obter(ofertaDto.getId());
        return ofertaDtoSave(ofertaDto);
    }
    public OfertaDto salvar(OfertaDto ofertaDto){
        Oferta oferta = ofertaMepper.toEntity(ofertaDto);
        Item item;
        item = itemMapper.toEntity(itemServico.obterPorId(oferta.getItem().getId()));

        UsuarioDto usuarioDtoProprietario = usuarioServico.obterPorId(item.getUsuario().getId());
        UsuarioDto usuarioDtoOfertante = usuarioServico.obterPorId(oferta.getUsuarioOfertante().getId());

        EmailDto emailDto = new EmailDto();
        emailDto.setAssunto("SEU PRODUTO TEM UMA NOVA OFERTA");
        emailDto.setDestinatario(usuarioDtoProprietario.getEmail());
        emailDto.setTexto("O senhor(a) " +
                usuarioDtoOfertante.getNome()+
                " ofereceu "+
                oferta.getItensOfertados().size() +
                " produto(s) pelo seu produto: " +
                item.getNome());

        ofertaRepositorio.save(oferta);

        enviarEmail(emailDto);
        return ofertaMepper.toDto(oferta);
    }
    public void deletar(Long id){
        Oferta oferta = ofertaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        ofertaRepositorio.delete(oferta);
    }

    private OfertaDto ofertaDtoSave(OfertaDto ofertaDto){
        Oferta oferta = ofertaMepper.toEntity(ofertaDto);
        ofertaRepositorio.save(oferta);
        return ofertaMepper.toDto(oferta);
    }
}
