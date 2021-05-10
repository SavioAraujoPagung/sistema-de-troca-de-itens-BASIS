package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.SistemadetrocadeitensApplication;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder.ItemBuilder;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder.UsuarioBuilder;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.ItemRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.UsuarioRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.ItemMapper;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.util.IntTestComum;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.util.TestUtil;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SistemadetrocadeitensApplication.class)
@Transactional
public class ItemRecursoIT extends IntTestComum {

    @Autowired
    private ItemBuilder itemBuilder;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemRepositorio itemRepositorio;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @BeforeEach
    public void inicializar(){
        usuarioRepositorio.deleteAll();
        usuarioBuilder.setCustomizacao(null);
        itemRepositorio.deleteAll();
        itemBuilder.setCustomizacao(null);
    }

    @Test
    public void listar() throws Exception {
        Usuario usuario = usuarioBuilder.construir();
        itemBuilder.customizar(entidade -> entidade.setUsuario(usuario)).construir();
        getMockMvc().perform(get("/api/item"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void listar2() throws Exception {
        Usuario usuario = usuarioBuilder.construir();
        itemBuilder.customizar(entidade -> {
            entidade.setNome("Item Teste A");
            entidade.setUsuario(usuario);
        }).construir();
        itemBuilder.customizar(entidade -> {
            entidade.setNome("Item Teste B");
            entidade.setUsuario(usuario);
        }).construir();
        getMockMvc().perform(get("/api/item"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void listarVazio() throws Exception {
        getMockMvc().perform(get("/api/item"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void obterPorId() throws Exception {
        Usuario usuario = usuarioBuilder.construir();
        Item item = itemBuilder.customizar(entidade -> entidade.setUsuario(usuario)).construir();
        getMockMvc().perform(get("/api/item/" + item.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void obterPorId2() throws Exception {
        Usuario usuario = usuarioBuilder.construir();
        itemBuilder.customizar(entidade -> {
            entidade.setNome("Item Teste A");
            entidade.setUsuario(usuario);
        }).construir();
        Item item = itemBuilder.customizar(entidade -> {
            entidade.setNome("Item Teste B");
            entidade.setUsuario(usuario);
        }).construir();
        itemBuilder.customizar(entidade -> {
            entidade.setNome("Item Teste C");
            entidade.setUsuario(usuario);
        }).construir();
        getMockMvc().perform(get("/api/item/" + item.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void obterPorIdInvalido() throws Exception {
        getMockMvc().perform(get("/api/item/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvar() throws Exception {
        Usuario usuario = usuarioBuilder.construir();
        Item item = itemBuilder.construirEntidade();
        item.setUsuario(usuario);
        getMockMvc().perform(post("/api/item")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(item))))
                .andExpect(status().isCreated());
    }

    @Test
    public void alterar() throws Exception{
        Usuario usuario = usuarioBuilder.construir();
        Item item = itemBuilder.customizar(entidade -> entidade.setUsuario(usuario)).construir();
        item.setNome("Nome Item Alterado");
        getMockMvc().perform(put("/api/item")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(item))))
                .andExpect(status().isOk());
    }

    @Test
    public void deletar() throws Exception{
        Usuario usuario = usuarioBuilder.construir();
        Item item = itemBuilder.customizar(entidade -> entidade.setUsuario(usuario)).construir();
        getMockMvc().perform(delete("/api/item/" + item.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarInvalido() throws Exception{
        Usuario usuario = usuarioBuilder.construir();
        Item item = itemBuilder.customizar(entidade -> entidade.setUsuario(usuario)).construir();
        item.setId(item.getId() + 5L);
        getMockMvc().perform(delete("/api/item/" + item.getId()))
                .andExpect(status().isBadRequest());
    }
}
