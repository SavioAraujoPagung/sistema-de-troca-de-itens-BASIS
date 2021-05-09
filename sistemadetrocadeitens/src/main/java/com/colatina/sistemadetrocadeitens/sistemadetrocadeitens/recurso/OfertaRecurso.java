package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.OfertaServico;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/oferta")
@RequiredArgsConstructor
public class OfertaRecurso {
    private final OfertaServico ofertaServico;

    @GetMapping("/{id}")
    public ResponseEntity<OfertaDto> obter(@PathVariable("id") Long id){
        OfertaDto ofertaDto = ofertaServico.obter(id);
        return new ResponseEntity<>(ofertaDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OfertaDto>> listar(){
        List<OfertaDto> ofertaListagemDto = ofertaServico.listar();
        return new ResponseEntity<>(ofertaListagemDto, HttpStatus.OK);
    }

    @PutMapping("/aceitar")
    public ResponseEntity<OfertaDto> aceitar(@Valid @RequestBody OfertaDto dto){
        OfertaDto ofertaDto = ofertaServico.aceitar(dto);
        return new ResponseEntity<OfertaDto>(ofertaDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OfertaDto> alterar(@Valid @RequestBody OfertaDto dto){
        OfertaDto ofertaDto = ofertaServico.alterar(dto);
        return new ResponseEntity<OfertaDto>(ofertaDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfertaDto> salvar(@Valid @RequestBody OfertaDto dto){
        OfertaDto ofertaDto = ofertaServico.salvar(dto);
        return new ResponseEntity<>(ofertaDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        ofertaServico.deletar(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
