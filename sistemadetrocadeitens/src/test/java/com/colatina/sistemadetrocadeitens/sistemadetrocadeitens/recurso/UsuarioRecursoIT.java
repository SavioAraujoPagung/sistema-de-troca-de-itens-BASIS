package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder.UsuarioBuilder;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Usuario;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.UsuarioRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.UsuarioServico;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.UsuarioMapper;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.util.IntTestComum;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.util.TestUtil;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
public class UsuarioRecursoIT extends IntTestComum {

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioServico usuarioServico;

    private UsuarioRepositorio usuarioRepositorio;

    @BeforeEach
    public void inicializar(){
        usuarioRepositorio.deleteAll();
        usuarioBuilder.setCustomizacao(null);
    }

    @Test
    public void listar() throws Exception {
        usuarioBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("api/usuario"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void listar2() throws  Exception {
        usuarioBuilder.construir();
        usuarioBuilder.customizar(entidade -> {
            entidade.setCpf("43543543534");
            entidade.setEmail("teste2@gmail.com");
        }).construir();
    }

    @Test
    public void salvar() throws Exception {
        Usuario usuario = usuarioBuilder.construirEntidade();
        getMockMvc().perform(MockMvcRequestBuilders.post("/api/usuario")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .contentType(TestUtil.convertObjectToJsonBytes(usuarioMapper.toDto(usuario))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
