package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;


import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfertaServico {
    public OfertaDto obter (){
        return null;
    }
    public List<OfertaDto> listar(){
        return null;
    }
    public Void alterar(OfertaDto ofertaDto){
        return null;
    }
    public OfertaDto salvar(OfertaDto ofertaDto){
        return null;
    }
    public Void deletar(OfertaDto ofertaDto){
        return null;
    }
}
