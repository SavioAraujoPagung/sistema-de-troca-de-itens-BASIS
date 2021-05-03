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
        UsuarioDto usuarioDto = usuarioServico.obterPorId(id);
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Void> alterar(UsuarioDto dto){
        usuarioServico.alterar(dto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> salvar(@RequestBody UsuarioDto dto){
        UsuarioDto usuarioDto = usuarioServico.salvar(dto);
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

    @DeleteMapping
    public  ResponseEntity<Void> deletar(@RequestBody UsuarioDto dto){
        usuarioServico.deletar(dto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
