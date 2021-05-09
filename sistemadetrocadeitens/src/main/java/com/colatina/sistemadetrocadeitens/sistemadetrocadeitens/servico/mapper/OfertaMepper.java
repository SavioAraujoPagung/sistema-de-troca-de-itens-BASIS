package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface OfertaMepper extends EntityMapper<OfertaDto, Oferta>{

    @AfterMapping
    default void mapearToEntity(OfertaDto ofertaDto, @MappingTarget Oferta oferta){
        oferta.setItensOfertados(ofertaDto.getItensOfertados().stream().map(id ->{
            Item item = new Item();
            item.setId(id);
            return item;
        }).collect(Collectors.toList()));
    }

    @AfterMapping
    default void mapearToDto(@MappingTarget OfertaDto ofertaDto, Oferta oferta){
        ofertaDto.setItensOfertados(oferta.getItensOfertados().stream().map(id ->{
            Long idDto = id.getId();
            return idDto;
        }).collect(Collectors.toList()));
    }

    @Override
    @Mapping(source = "usuarioOfertanteId", target = "usuarioOfertante.id")
    @Mapping(source = "itemId", target = "item.id")
    @Mapping(source = "situacaoId", target = "situacao.id")
    @Mapping(target = "itensOfertados", ignore = true)
    Oferta toEntity(OfertaDto dto);

    @Override
    @InheritInverseConfiguration
    @Mapping(target = "itensOfertados", ignore = true)
    OfertaDto toDto(Oferta dto);

}
