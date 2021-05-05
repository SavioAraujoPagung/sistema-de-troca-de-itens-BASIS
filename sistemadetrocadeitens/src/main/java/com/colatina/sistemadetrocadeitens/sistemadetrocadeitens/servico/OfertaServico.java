package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;


import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.OfertaRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
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

    public OfertaDto obter (Long id){
        Oferta oferta = ofertaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Oferta " +
                "não encontrada"));
        return ofertaMepper.toDto(oferta);
    }

    public List<OfertaDto> listar(){
        List<Oferta> oferta = ofertaRepositorio.findAll();
        return ofertaMepper.toDto(oferta);
    }
    public OfertaDto alterar(OfertaDto ofertaDto){
        obter(ofertaDto.getId());
        return OfertaDtoSave(ofertaDto);
    }
    public OfertaDto salvar(OfertaDto ofertaDto){
        return OfertaDtoSave(ofertaDto);
    }
    public void deletar(Long id){
        Oferta oferta = ofertaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        ofertaRepositorio.delete(oferta);
    }


    private OfertaDto OfertaDtoSave(OfertaDto ofertaDto){
        Oferta oferta = ofertaMepper.toEntity(ofertaDto);
        ofertaRepositorio.save(oferta);
        return ofertaMepper.toDto(oferta);
    }
}
