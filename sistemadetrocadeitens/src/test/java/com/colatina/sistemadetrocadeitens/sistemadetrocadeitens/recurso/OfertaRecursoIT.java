package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.SistemadetrocadeitensApplication;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder.ItemBuilder;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder.OfertaBuilder;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder.UsuarioBuilder;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Oferta;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.ItemRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.OfertaRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.UsuarioRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.OfertaMapper;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.util.IntTestComum;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SistemadetrocadeitensApplication.class)
@Transactional
public class OfertaRecursoIT extends IntTestComum {

    @Autowired
    private OfertaBuilder ofertaBuilder;

    @Autowired
    private OfertaMapper ofertaMapper;

    @Autowired
    OfertaRepositorio ofertaRepositorio;

    @Autowired
    private ItemBuilder itemBuilder;

    @Autowired
    private ItemRepositorio itemRepositorio;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    private Oferta criarOferta(Usuario usuarioOfertante, Item itemDesejado, List<Item> itensOfertados){
        Oferta oferta = ofertaBuilder.construir();

        oferta.setUsuarioOfertante(usuarioOfertante);
        oferta.setItem(itemDesejado);
        oferta.setItensOfertados(itensOfertados);

        return oferta;
    }

    @BeforeEach
    public void inicializar(){
        usuarioRepositorio.deleteAll();
        usuarioBuilder.setCustomizacao(null);
        itemRepositorio.deleteAll();
        itemBuilder.setCustomizacao(null);
        ofertaRepositorio.deleteAll();
        ofertaBuilder.customizar(null);
    }

    @Test
    public void listar() throws Exception {
    }

    @Test
    public void listar2() throws Exception {
    }

    @Test
    public void listarVazio() throws Exception {
        getMockMvc().perform(get("/api/oferta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void obterPorId() throws Exception {
    }

    @Test
    public void obterPorIdInvalido() throws Exception {
        getMockMvc().perform(get("/api/oferta/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvar() throws Exception {
    }

    @Test
    public void alterar() throws Exception{
    }

    @Test
    public void deletar() throws Exception{
    }

    @Test
    public void deletarInvalido() throws Exception{
    }
}
