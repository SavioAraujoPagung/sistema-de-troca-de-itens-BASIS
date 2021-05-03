package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDto, Item>{

    @Override
    @Mapping(source = "categoriaId", target = "tb_categoria.id")
    @Mapping(source = "usuarioId", target = "tb_usuario.id")
    Item toEntity(ItemDto dto);

    @Override
    @Mapping(source = "tb_categoria.id", target = "categoriaId")
    @Mapping(source = "tb_usuario.id", target = "usuarioId")
    ItemDto toDto(Item entity);
}
