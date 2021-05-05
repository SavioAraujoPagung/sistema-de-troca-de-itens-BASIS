package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepositorio extends JpaRepository<Oferta, Long> {
    /*
    * @Query("SELECT new com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto" +
            "(i.id, i.nome, i.imagem, i.descricao, ic.id, iu.id)
            "FROM Item i
            "JOIN i.categoria ic JOIN i.usuario iu")
    * */
    @Query("SELECT " +
           "NEW com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto" +
            "(o.id, o.situacao.descricao, oi.nome, (SELECT id " +
                                                   "FROM ois )) " +
           "FROM Oferta o " +
           "JOIN o.item oi " +
           "JOIN o.itensOfertados ois")
    List<OfertaDto> listarOfertaDto();
}
