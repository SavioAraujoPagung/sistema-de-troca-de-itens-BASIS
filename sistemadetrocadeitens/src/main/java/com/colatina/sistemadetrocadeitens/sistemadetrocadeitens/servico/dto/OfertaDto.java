package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Situacao;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfertaDto {

    private Long id;

    private Situacao situacao;

    private Long usuarioOfertanteId;

    private Long itemId;

    private List<ItemDto> itensOfertadosId;

}
