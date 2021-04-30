package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituacaoRepositorio extends JpaRepository<Situacao, Long> {
}
