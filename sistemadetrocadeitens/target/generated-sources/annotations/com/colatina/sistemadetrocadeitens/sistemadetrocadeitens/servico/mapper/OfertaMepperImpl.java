package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-07T18:50:50-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_292 (Private Build)"
)
@Component
public class OfertaMepperImpl implements OfertaMepper {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Oferta> toEntity(List<OfertaDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Oferta> list = new ArrayList<Oferta>( dtoList.size() );
        for ( OfertaDto ofertaDto : dtoList ) {
            list.add( toEntity( ofertaDto ) );
        }

        return list;
    }

    @Override
    public List<OfertaDto> toDto(List<Oferta> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<OfertaDto> list = new ArrayList<OfertaDto>( entityList.size() );
        for ( Oferta oferta : entityList ) {
            list.add( toDto( oferta ) );
        }

        return list;
    }

    @Override
    public Oferta toEntity(OfertaDto dto) {
        if ( dto == null ) {
            return null;
        }

        Oferta oferta = new Oferta();

        oferta.setUsuarioOfertante( ofertaDtoToUsuario( dto ) );
        oferta.setItem( ofertaDtoToItem( dto ) );
        oferta.setId( dto.getId() );
        oferta.setItensOfertados( itemMapper.toEntity( dto.getItensOfertados() ) );

        return oferta;
    }

    @Override
    public OfertaDto toDto(Oferta dto) {
        if ( dto == null ) {
            return null;
        }

        OfertaDto ofertaDto = new OfertaDto();

        ofertaDto.setUsuarioOfertanteId( dtoUsuarioOfertanteId( dto ) );
        ofertaDto.setItemId( dtoItemId( dto ) );
        ofertaDto.setId( dto.getId() );
        ofertaDto.setItensOfertados( itemMapper.toDto( dto.getItensOfertados() ) );

        return ofertaDto;
    }

    protected Usuario ofertaDtoToUsuario(OfertaDto ofertaDto) {
        if ( ofertaDto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( ofertaDto.getUsuarioOfertanteId() );

        return usuario;
    }

    protected Item ofertaDtoToItem(OfertaDto ofertaDto) {
        if ( ofertaDto == null ) {
            return null;
        }

        Item item = new Item();

        item.setId( ofertaDto.getItemId() );

        return item;
    }

    private Long dtoUsuarioOfertanteId(Oferta oferta) {
        if ( oferta == null ) {
            return null;
        }
        Usuario usuarioOfertante = oferta.getUsuarioOfertante();
        if ( usuarioOfertante == null ) {
            return null;
        }
        Long id = usuarioOfertante.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long dtoItemId(Oferta oferta) {
        if ( oferta == null ) {
            return null;
        }
        Item item = oferta.getItem();
        if ( item == null ) {
            return null;
        }
        Long id = item.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
