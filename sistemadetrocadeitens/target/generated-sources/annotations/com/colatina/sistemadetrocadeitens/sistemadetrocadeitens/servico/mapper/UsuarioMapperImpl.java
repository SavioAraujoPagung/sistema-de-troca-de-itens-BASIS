package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-10T10:13:34-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioDto dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( dto.getId() );
        usuario.setNome( dto.getNome() );
        usuario.setCpf( dto.getCpf() );
        usuario.setEmail( dto.getEmail() );
        usuario.setData( dto.getData() );

        return usuario;
    }

    @Override
    public UsuarioDto toDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId( entity.getId() );
        usuarioDto.setNome( entity.getNome() );
        usuarioDto.setCpf( entity.getCpf() );
        usuarioDto.setEmail( entity.getEmail() );
        usuarioDto.setData( entity.getData() );

        return usuarioDto;
    }

    @Override
    public List<Usuario> toEntity(List<UsuarioDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( dtoList.size() );
        for ( UsuarioDto usuarioDto : dtoList ) {
            list.add( toEntity( usuarioDto ) );
        }

        return list;
    }

    @Override
    public List<UsuarioDto> toDto(List<Usuario> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UsuarioDto> list = new ArrayList<UsuarioDto>( entityList.size() );
        for ( Usuario usuario : entityList ) {
            list.add( toDto( usuario ) );
        }

        return list;
    }
}
