package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDto {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private LocalDate data;
}
