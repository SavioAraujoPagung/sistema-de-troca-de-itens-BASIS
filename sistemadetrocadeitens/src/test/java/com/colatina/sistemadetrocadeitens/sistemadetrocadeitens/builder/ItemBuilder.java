package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.builder;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.ItemServico;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.apache.commons.lang.ArrayUtils;

import java.nio.charset.StandardCharsets;

@Component
public class ItemBuilder extends ConstrutorEntidade<Item>{

    @Autowired
    private ItemServico itemServico;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Override
    public Item construirEntidade() {
        Item item = new Item();
        item.setNome("Item Teste");
        item.setDisponibilidade(true);
        item.setDescricao("Descrição do Item Teste");
        item.setImagem(iniciarImagem());
        item.getCategoria().setId(1L);
        item.getUsuario().setId(usuarioBuilder.construir().getId());
        return item;
    }

    @Override
    public Item persistir(Item entidade) {
        ItemDto dto = itemMapper.toDto(entidade);
        return itemMapper.toEntity(itemServico.salvar(dto));
    }

    private Byte[] iniciarImagem(){
        String str = "Byte array de imagem para Item teste";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return ArrayUtils.toObject(bytes);
    }
}
