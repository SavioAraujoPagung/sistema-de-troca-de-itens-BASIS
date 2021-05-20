package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioListagemDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-20T18:28:54-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_292 (Private Build)"
)
@Component
public class UsuarioListagemMapperImpl implements UsuarioListagemMapper {

    @Override
    public Usuario toEntity(UsuarioListagemDto dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( dto.getId() );
        usuario.setNome( dto.getNome() );
        usuario.setEmail( dto.getEmail() );
        usuario.setData( dto.getData() );

        return usuario;
    }

    @Override
    public UsuarioListagemDto toDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioListagemDto usuarioListagemDto = new UsuarioListagemDto();

        usuarioListagemDto.setId( entity.getId() );
        usuarioListagemDto.setNome( entity.getNome() );
        usuarioListagemDto.setEmail( entity.getEmail() );
        usuarioListagemDto.setData( entity.getData() );

        return usuarioListagemDto;
    }

    @Override
    public List<Usuario> toEntity(List<UsuarioListagemDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( dtoList.size() );
        for ( UsuarioListagemDto usuarioListagemDto : dtoList ) {
            list.add( toEntity( usuarioListagemDto ) );
        }

        return list;
    }

    @Override
    public List<UsuarioListagemDto> toDto(List<Usuario> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UsuarioListagemDto> list = new ArrayList<UsuarioListagemDto>( entityList.size() );
        for ( Usuario usuario : entityList ) {
            list.add( toDto( usuario ) );
        }

        return list;
    }
}
