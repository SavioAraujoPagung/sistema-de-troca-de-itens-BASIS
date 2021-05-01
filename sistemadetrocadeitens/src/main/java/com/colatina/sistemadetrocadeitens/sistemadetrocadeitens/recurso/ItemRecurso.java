package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.ItemServico;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemRecurso {

    private final ItemServico itemServico;

    @GetMapping
    public ResponseEntity<List<ItemDto>> listar(){
        List<ItemDto> itemDtos = itemServico.listar();
        return new ResponseEntity<>(itemDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable("id") Long id){
        ItemDto itemDto = itemServico.findById(id);
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemDto> salvar(@RequestBody ItemDto dto){
        ItemDto itemDto = itemServico.salvar(dto);
        return  new ResponseEntity<>(itemDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> alterar(@RequestBody ItemDto dto){
        itemServico.alterar(dto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        itemServico.deletar(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}