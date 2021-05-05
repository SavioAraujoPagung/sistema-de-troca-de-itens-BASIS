package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Categoria;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto{

    private Long id;

    private String nome;

    @Lob
    private Byte[] imagem;

    private String descricao;

    private Boolean disponibilidade;

    private Long categoriaId;

    private Long usuarioId;

}