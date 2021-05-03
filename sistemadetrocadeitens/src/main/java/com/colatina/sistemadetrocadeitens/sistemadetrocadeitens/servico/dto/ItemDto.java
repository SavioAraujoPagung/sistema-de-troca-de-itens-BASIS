package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Categoria;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
public class ItemDto{

    private Long id;

    private String nome;

    @Lob
    private Byte[] imagem;

    private String descricao;

    private Long id_categoria;

    private Long id_usuario;

}