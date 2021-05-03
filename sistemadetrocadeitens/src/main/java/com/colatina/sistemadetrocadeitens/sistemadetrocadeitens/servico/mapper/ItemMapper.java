package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDto, Item>{

    @Override
    @Mapping(source = "id_categoria", target = "tb_categoria.id")
    @Mapping(source = "id_usuario", target = "tb_usuario.id")
    Item toEntity(ItemDto dto);

    @Override
    @Mapping(source = "tb_categoria.id", target = "id_categoria")
    @Mapping(source = "tb_usuario.id", target = "id_usuario")
    ItemDto toDto(Item entity);
}
