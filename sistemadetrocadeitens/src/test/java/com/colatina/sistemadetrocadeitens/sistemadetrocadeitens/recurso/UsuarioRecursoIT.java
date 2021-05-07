package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.SistemadetrocadeitensApplication;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder.UsuarioBuilder;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.UsuarioRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.UsuarioMapper;
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

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SistemadetrocadeitensApplication.class)
@Transactional
public class UsuarioRecursoIT extends IntTestComum {

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @BeforeEach
    public void inicializar(){
        usuarioRepositorio.deleteAll();
        usuarioBuilder.setCustomizacao(null);
    }

    @Test
    public void listar() throws Exception {
        usuarioBuilder.construir();
        getMockMvc().perform(get("/api/usuario"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void listar2() throws Exception {
        usuarioBuilder.construir();
        usuarioBuilder.customizar(entidade -> {
            entidade.setCpf("43543543534");
            entidade.setEmail("teste2@gmail.com");
        }).construir();
        getMockMvc().perform(get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void listarVazio() throws Exception {
        getMockMvc().perform(get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void obterPorId() throws Exception {
        Usuario usuario = usuarioBuilder.construir();
        getMockMvc().perform(get("/api/usuario/" + usuario.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void obterPorId2() throws Exception {
        usuarioBuilder.construir();
        Usuario usuario = usuarioBuilder.customizar(entidade -> {
            entidade.setCpf("24603471033");
            entidade.setEmail("teste2@gmail.com");
        }).construir();
        usuarioBuilder.customizar(entidade -> {
            entidade.setCpf("24877455094");
            entidade.setEmail("teste3@gmail.com");
        }).construir();
        getMockMvc().perform(get("/api/usuario/" + usuario.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void obterPorIdInvalido() throws Exception {
        Usuario usuario = usuarioBuilder.construir();
        usuario.setId(usuario.getId() + 5L);
        getMockMvc().perform(get("/api/usuario/" + usuario.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvar() throws Exception {
        Usuario usuario = usuarioBuilder.construirEntidade();
        getMockMvc().perform(post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isCreated());
    }

    @Test
    public void salvarNomeInvalido() throws Exception {
        Usuario usuario = usuarioBuilder.construirEntidade();
        usuario.setNome("");
        getMockMvc().perform(post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarCpfInvalido() throws Exception {
        Usuario usuario = usuarioBuilder.construirEntidade();
        usuario.setCpf("11122233344");
        getMockMvc().perform(post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarEmailInvalido() throws Exception {
        Usuario usuario = usuarioBuilder.construirEntidade();
        usuario.setEmail("abcdefg");
        getMockMvc().perform(post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarDataInvalido() throws Exception {
        usuarioBuilder.construir();
        Usuario usuario = usuarioBuilder.construirEntidade();
        usuario.setData(LocalDate.now().plusDays(5L));
        getMockMvc().perform(post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarCpfDuplicado() throws Exception {
        usuarioBuilder.customizar(entidade -> {
            entidade.setEmail("teste2@gmail.com");
        }).construir();
        Usuario usuario = usuarioBuilder.construirEntidade();
        getMockMvc().perform(post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarEmailDuplicado() throws Exception {
        usuarioBuilder.customizar(entidade -> {
            entidade.setCpf("24877455094");
        }).construir();
        Usuario usuario = usuarioBuilder.construirEntidade();
        getMockMvc().perform(post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void alterar() throws Exception{
        Usuario usuarioSalvo = usuarioBuilder.construir();
        usuarioSalvo.setNome("Teste Usuario 2");
        getMockMvc().perform(put("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuarioSalvo))))
                .andExpect(status().isOk());
    }

    @Test
    public void alterarNomeInvalido() throws Exception{
        Usuario usuarioSalvo = usuarioBuilder.construir();
        usuarioSalvo.setNome("");
        getMockMvc().perform(put("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuarioSalvo))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void alterarCpfInvalido() throws Exception{
        Usuario usuarioSalvo = usuarioBuilder.construir();
        usuarioSalvo.setCpf("11122233344");
        getMockMvc().perform(put("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuarioSalvo))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void alterarEmailInvalido() throws Exception{
        Usuario usuarioSalvo = usuarioBuilder.construir();
        usuarioSalvo.setEmail("abcdefg");
        getMockMvc().perform(put("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuarioSalvo))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void alterarDataInalida() throws Exception{
        Usuario usuarioSalvo = usuarioBuilder.construir();
        usuarioSalvo.setData(LocalDate.now().plusDays(5L));
        getMockMvc().perform(put("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuarioSalvo))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletar() throws Exception{
        Usuario usuario = usuarioBuilder.construir();
        getMockMvc().perform(delete("/api/usuario/" + usuario.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarInvalido() throws Exception{
        Usuario usuario = usuarioBuilder.construir();
        usuario.setId(usuario.getId() + 5L);
        getMockMvc().perform(delete("/api/usuario/" + usuario.getId()))
                .andExpect(status().isBadRequest());
    }
}
