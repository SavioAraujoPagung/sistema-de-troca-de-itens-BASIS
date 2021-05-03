package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Situacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaDto {

    private Long id;

    private Situacao situacao;

    private UsuarioDto usuarioOfertanteDto;

}
