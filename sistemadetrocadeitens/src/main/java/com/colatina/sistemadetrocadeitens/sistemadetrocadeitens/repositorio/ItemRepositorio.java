package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositorio extends JpaRepository<Item, Long> {
    @Query("SELECT new com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto" +
            "(i.id, i.nome, i.imagem, i.descricao, ic.id, iu.id) FROM Item i " +
            "JOIN i.categoria ic JOIN i.usuario iu")
    List<ItemDto> listarItem();
}
