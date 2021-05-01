package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Categoria;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto{

    private Long id;

    private String nome;

    private Byte[] imagem;

    private String descricao;

    private Categoria categoria;

    private Usuario usuario;

}