package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.UsuarioServico;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioListagemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioRecurso {

    private final UsuarioServico usuarioServico;

    @GetMapping
    public ResponseEntity<List<UsuarioListagemDto>> listar(){

        List<UsuarioListagemDto> usuarioListagemDto = usuarioServico.listar();
        return new ResponseEntity<>(usuarioListagemDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable("id") Long id){
        UsuarioDto usuarioDto = usuarioServico.findById(id);
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Void> alterar(UsuarioDto dto){
        UsuarioDto usuarioDto = usuarioServico.alterar(usuariodto);
        return new ResponseEntity<Void>(usuarioDto, HttpStatus.OK);
    }

}
