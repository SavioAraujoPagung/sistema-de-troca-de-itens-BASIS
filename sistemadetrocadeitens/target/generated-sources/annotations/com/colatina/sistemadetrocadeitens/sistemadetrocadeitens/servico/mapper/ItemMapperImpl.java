package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Categoria;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-04T10:06:43-0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_292 (Private Build)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public List<Item> toEntity(List<ItemDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Item> list = new ArrayList<Item>( arg0.size() );
        for ( ItemDto itemDto : arg0 ) {
            list.add( toEntity( itemDto ) );
        }

        return list;
    }

    @Override
    public List<ItemDto> toDto(List<Item> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ItemDto> list = new ArrayList<ItemDto>( arg0.size() );
        for ( Item item : arg0 ) {
            list.add( toDto( item ) );
        }

        return list;
    }

    @Override
    public Item toEntity(ItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        Item item = new Item();

        item.setCategoria( itemDtoToCategoria( dto ) );
        item.setUsuario( itemDtoToUsuario( dto ) );
        item.setId( dto.getId() );
        item.setNome( dto.getNome() );
        Byte[] imagem = dto.getImagem();
        if ( imagem != null ) {
            item.setImagem( Arrays.copyOf( imagem, imagem.length ) );
        }
        item.setDescricao( dto.getDescricao() );

        return item;
    }

    @Override
    public ItemDto toDto(Item entity) {
        if ( entity == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setCategoriaId( entityCategoriaId( entity ) );
        itemDto.setUsuarioId( entityUsuarioId( entity ) );
        itemDto.setId( entity.getId() );
        itemDto.setNome( entity.getNome() );
        Byte[] imagem = entity.getImagem();
        if ( imagem != null ) {
            itemDto.setImagem( Arrays.copyOf( imagem, imagem.length ) );
        }
        itemDto.setDescricao( entity.getDescricao() );

        return itemDto;
    }

    protected Categoria itemDtoToCategoria(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( itemDto.getCategoriaId() );

        return categoria;
    }

    protected Usuario itemDtoToUsuario(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( itemDto.getUsuarioId() );

        return usuario;
    }

    private Long entityCategoriaId(Item item) {
        if ( item == null ) {
            return null;
        }
        Categoria categoria = item.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        Long id = categoria.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityUsuarioId(Item item) {
        if ( item == null ) {
            return null;
        }
        Usuario usuario = item.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long id = usuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
