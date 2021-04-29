package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_situacao-oferta")
public class SituacaoOferta {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;
}
