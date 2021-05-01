package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.UsuarioRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioListagemDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.exception.RegraNegocioException;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.UsuarioListagemMapper;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServico {
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioListagemMapper usuarioListagemMapper;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioListagemDto> listar(){
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarioListagemMapper.toDto(usuarios);
    }

    public UsuarioDto obterPorId(Long id){
        Usuario usuario = usuarioRepositorio.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Usuario nao encontrado"));
        return usuarioMapper.toDto(usuario);
    }
    public UsuarioDto alterar(UsuarioDto usuarioDto){
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        UsuarioDto dto = usuarioMapper.toDto(usuarioRepositorio.save(usuario));
        return dto;
    }

    public UsuarioDto salvar(UsuarioDto dto){
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuarioRepositorio.save(usuario);
        UsuarioDto usuarioDto = usuarioMapper.toDto(usuario);
        return usuarioDto;
    }

    public Void deletar(UsuarioDto dto){
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuarioRepositorio.delete(usuario);
        return null;
    }
}
