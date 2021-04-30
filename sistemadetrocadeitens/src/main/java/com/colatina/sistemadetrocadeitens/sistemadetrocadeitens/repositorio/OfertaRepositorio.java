package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertaRepositorio extends JpaRepository<Oferta, Long> {
}
