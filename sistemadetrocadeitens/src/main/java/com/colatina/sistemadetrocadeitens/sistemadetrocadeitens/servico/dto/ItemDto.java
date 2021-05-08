package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

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

    public ItemDto (Long id){
        setId(id);
    }

}