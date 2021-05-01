package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDto, Item>{
}
