package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.ItemServico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemRecurso {

    private final ItemServico itemServico;

    @GetMapping
    public int listar(){
        return 1;
    }

    @GetMapping("/{id}")
    public int findById(@PathVariable("id") Long id){
        return 1;
    }

    @PostMapping
    public int salvar(){
        return 1;
    }

    @PutMapping
    public int alterar(){
        return 1;
    }

    @DeleteMapping
    public void apagar(){
    }

}