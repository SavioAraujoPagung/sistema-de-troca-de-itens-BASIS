package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioListagemDto {

    private Long id;

    private String nome;

    private String email;

    private LocalDate data;
}
