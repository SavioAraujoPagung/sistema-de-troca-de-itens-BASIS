package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaListagemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepositorio extends JpaRepository<Oferta, Long> {
    @Query("SELECT new com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaListagemDto" +
            "(o.id, situacao.id, ofertante.id, disponivel.id) FROM Oferta o " +
            "JOIN o.item disponivel JOIN o.usuarioOfertante ofertante JOIN o.situacao situacao")
    List<OfertaListagemDto> listarOferta();

    @Query("SELECT new com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaListagemDto" +
            "(o.id, situacao.id, ofertante.id, disponivel.id) FROM Oferta o " +
            "JOIN o.item disponivel JOIN o.usuarioOfertante ofertante JOIN o.situacao situacao " +
            "WHERE ofertante.id = :ofertanteId")
    List<OfertaListagemDto> listarOfertaPorOfertante(@Param("ofertanteId") Long UsuarioOfertanteId);

    List<Oferta> findAllBySituacao_Id(Long id);
}
