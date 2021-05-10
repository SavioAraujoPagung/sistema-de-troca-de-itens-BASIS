package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface OfertaRepositorio extends JpaRepository<Oferta, Long> {
    List<Oferta> findAllBySituacao_Id(Long id);

    @Query(value = "update Oferta o set o.situacao.id = :idSituacao " +
                   "where o.item.id = :idItem and not o.id = :idOferta")
    void cancelar(@PathVariable("idSituacao") Long idSituacao,
                  @PathVariable ("idTem")Long idItem,
                  @PathVariable("idOferta") Long idOferta);

    @Query(value = "update Oferta o set o.situacao.id = :idSituacao " +
            "where o.id =: idOferta")
    OfertaDto aceitar(@PathVariable("idSituacao") Long idSituacao,
                      @PathVariable("idOferta") Long idOferta);

}
