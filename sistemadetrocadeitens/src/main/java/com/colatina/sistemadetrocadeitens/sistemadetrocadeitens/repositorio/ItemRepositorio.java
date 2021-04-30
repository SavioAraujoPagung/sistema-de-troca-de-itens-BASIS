package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositorio extends JpaRepository<Item, Long> {
}
