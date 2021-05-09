package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface OfertaMepper extends EntityMapper<OfertaDto, Oferta>{

    @Override
    @Mapping(source = "usuarioOfertanteId", target = "usuarioOfertante.id")
    @Mapping(source = "itemId", target = "item.id")
    @Mapping(source = "situacaoId", target = "situacao.id")
    Oferta toEntity(OfertaDto dto);

    @Override
    @InheritInverseConfiguration
    OfertaDto toDto(Oferta dto);

}
