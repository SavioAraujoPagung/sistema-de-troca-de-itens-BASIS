package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-03T14:08:39-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item toEntity(ItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        Item item = new Item();

        return item;
    }

    @Override
    public ItemDto toDto(Item entity) {
        if ( entity == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        return itemDto;
    }

    @Override
    public List<Item> toEntity(List<ItemDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Item> list = new ArrayList<Item>( dtoList.size() );
        for ( ItemDto itemDto : dtoList ) {
            list.add( toEntity( itemDto ) );
        }

        return list;
    }

    @Override
    public List<ItemDto> toDto(List<Item> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ItemDto> list = new ArrayList<ItemDto>( entityList.size() );
        for ( Item item : entityList ) {
            list.add( toDto( item ) );
        }

        return list;
    }
}
