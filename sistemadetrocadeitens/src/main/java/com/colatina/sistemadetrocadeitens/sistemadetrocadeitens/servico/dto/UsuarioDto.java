package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

import javax.persistence.*;
import java.time.LocalDate;

public class UsuarioDto {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private LocalDate data;
}
