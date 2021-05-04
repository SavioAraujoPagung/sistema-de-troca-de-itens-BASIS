package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.UsuarioRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioListagemDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.exception.RegraNegocioException;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.UsuarioListagemMapper;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

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
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuario nao encontrado"));
        return usuarioMapper.toDto(usuario);
    }
    public UsuarioDto alterar(UsuarioDto usuarioDto){
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        UsuarioDto dto = usuarioMapper.toDto(usuarioRepositorio.save(usuario));
        return dto;
    }

    public UsuarioDto salvar(UsuarioDto dto){
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setToken(UUID.randomUUID().toString().replace("-", ""));
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public void deletar(Long id){
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuario nao encontrado"));
        usuarioRepositorio.delete(usuario);
    }
}
