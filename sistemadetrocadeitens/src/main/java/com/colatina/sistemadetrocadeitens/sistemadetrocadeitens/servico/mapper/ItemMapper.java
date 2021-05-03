package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDto, Item>{

    @Override
    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(source = "usuarioId", target = "usuario.id")
    Item toEntity(ItemDto dto);

    @Override
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    ItemDto toDto(Item entity);
}
