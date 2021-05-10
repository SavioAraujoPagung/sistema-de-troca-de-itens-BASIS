package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.recurso;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.SistemadetrocadeitensApplication;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder.ItemBuilder;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.ItemRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.ItemMapper;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.util.IntTestComum;
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

    @BeforeEach
    public void inicializar(){
        itemRepositorio.deleteAll();
        itemBuilder.setCustomizacao(null);
    }


}
