package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface OfertaMepper extends EntityMapper<OfertaDto, Oferta>{

    @Override
    @Mapping(source = "usuarioOfertanteId", target = "usuarioOfertante.id")
    @Mapping(source = "itemId", target = "item.id")
    Oferta toEntity(OfertaDto dto);

    @Override
    @Mapping(source = "usuarioOfertante.id", target = "usuarioOfertanteId")
    @Mapping(source = "item.id", target = "itemId")
    OfertaDto toDto(Oferta dto);

}
