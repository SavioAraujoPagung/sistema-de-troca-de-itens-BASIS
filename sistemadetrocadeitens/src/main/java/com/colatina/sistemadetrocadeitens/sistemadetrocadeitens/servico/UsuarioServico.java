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
import org.springframework.transaction.annotation.Transactional;

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
        return usuarioRepositorio.listarUsuario();
    }

    public UsuarioDto obterPorId(Long id){
        UsuarioDto usuarioDto = usuarioRepositorio.obterUsuarioPorId(id);
        if (usuarioDto == null){
            throw(new RegraNegocioException("Usuario nao encontrado"));
        }
        return usuarioDto;
    }

    public UsuarioDto alterar(UsuarioDto usuarioDto){
        validarCpf(usuarioDto);
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        Usuario usuarioSalvo = usuarioRepositorio.findById(usuario.getId()).orElseThrow(() -> new RegraNegocioException("Usuario nao encontrado"));
        usuario.setToken(usuarioSalvo.getToken());
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDto salvar(UsuarioDto dto){
        validarCpf(dto);
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setToken(UUID.randomUUID().toString().replace("-", ""));
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public void deletar(Long id){
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Usuario nao encontrado"));
        usuarioRepositorio.delete(usuario);
    }

    private void validarCpf(UsuarioDto dto){
        Usuario usuario = usuarioRepositorio.findByCpf(dto.getCpf());
        if (usuario != null && !usuario.getId().equals(dto.getId())){
            throw new RegraNegocioException("CPF já cadastrado");
        }
    }
}
