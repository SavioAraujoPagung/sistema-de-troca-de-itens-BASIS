package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;


import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.OfertaRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.exception.RegraNegocioException;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.OfertaMepper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfertaServico {

    private final OfertaRepositorio ofertaRepositorio;
    private final OfertaMepper ofertaMepper;
    private final ItemServico itemServico;

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
        return OfertaDtoSave(ofertaDto);
    }

    public void deletar(Long id){
        Oferta oferta = ofertaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        ofertaRepositorio.delete(oferta);
    }

    public OfertaDto aceitar(Long id){
        OfertaDto ofertaDto = obterPorId(id);
        ofertaDto.setSituacaoId(2L);
        return ofertaDto;
    }

    public OfertaDto cancelar(Long id){
        OfertaDto ofertaDto = obterPorId(id);
        ofertaDto.setSituacaoId(2L);
        return ofertaDto;
    }

    public OfertaDto recusar(Long id){
        OfertaDto ofertaDto = obterPorId(id);
        ofertaDto.setSituacaoId(2L);
        return ofertaDto;
    }

    private OfertaDto OfertaDtoSave(OfertaDto ofertaDto){
        Oferta oferta = ofertaMepper.toEntity(ofertaDto);
        ofertaRepositorio.save(oferta);
        return ofertaMepper.toDto(oferta);
    }

    private void trocarItemDisponivel(OfertaDto ofertaDto){
        ItemDto itemDto = itemServico.obterPorId(ofertaDto.getItemId());
        trocarItem(itemDto, ofertaDto.getUsuarioOfertanteId());
    }

    private void trocarItemOfertado(OfertaDto ofertaDto){
        UsuarioDto usuarioDto = itemServico.obterDono(ofertaDto.getItemId());
        ofertaDto.getItensOfertados().forEach(itemOfertado -> {
            trocarItem(itemOfertado, usuarioDto.getId());
        });
    }

    private void trocarItem(ItemDto itemDto, Long novoUsuario){
        itemDto.setDisponibilidade(false);
        itemDto.setUsuarioId(novoUsuario);
        itemServico.alterar(itemDto);
    }
}
