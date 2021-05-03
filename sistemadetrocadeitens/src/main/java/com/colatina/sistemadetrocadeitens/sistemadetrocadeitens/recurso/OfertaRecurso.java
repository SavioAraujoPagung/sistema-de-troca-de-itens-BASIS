package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.OfertaServico;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oferta")
@RequiredArgsConstructor
public class OfertaRecurso {
    private final OfertaServico ofertaServico;

    @GetMapping("/{id}")
    public ResponseEntity<OfertaDto> obter(@PathVariable("id") Long id){
        OfertaDto ofertaDto = ofertaServico.obter();
        return new ResponseEntity<>(ofertaDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OfertaDto>> listar(){
        List<OfertaDto> ofertaListagemDto = ofertaServico.listar();
        return new ResponseEntity<>(ofertaListagemDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> alterar(@RequestBody OfertaDto dto){
        ofertaServico.alterar(dto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfertaDto> salvar(@RequestBody OfertaDto dto){
        OfertaDto ofertaDto = ofertaServico.salvar(dto);
        return new ResponseEntity<>(ofertaDto, HttpStatus.OK);
    }
    @DeleteMapping
    public  ResponseEntity<Void> deletar(@RequestBody OfertaDto dto){
        ofertaServico.deletar(dto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
