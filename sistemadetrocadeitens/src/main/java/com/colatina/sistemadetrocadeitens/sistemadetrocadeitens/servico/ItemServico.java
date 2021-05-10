package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.dominio.Item;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.repositorio.ItemRepositorio;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.ItemDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.exception.RegraNegocioException;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServico {

    private final ItemRepositorio itemRepositorio;
    private final ItemMapper itemMapper;
    private final UsuarioServico usuarioServico;

    public List<ItemDto> listar(){
        return itemRepositorio.listarItem();
    }

    public ItemDto obterPorId(Long id){
        ItemDto itemDto = itemRepositorio.obterItemPorId(id);
        if (itemDto == null){
            throw(new RegraNegocioException("Item nao encontrado"));
        }
        return itemDto;
    }

    public ItemDto salvar(ItemDto itemDto){
        usuarioServico.obterPorId(itemDto.getUsuarioId());
        Item item = itemMapper.toEntity(itemDto);
        itemRepositorio.save(item);
        return itemMapper.toDto(item);
    }

    public List<ItemDto> salvarVarios(List<ItemDto> itensDto){
        List<Item> itens = itemMapper.toEntity(itensDto);
        itemRepositorio.saveAll(itens);
        return itemMapper.toDto(itens);
    }

    public ItemDto alterar(ItemDto itemDto){
        usuarioServico.obterPorId(itemDto.getUsuarioId());
        Item item = itemMapper.toEntity(itemDto);
        itemRepositorio.save(item);
        return itemMapper.toDto(item);
    }

    public void deletar(Long id){
        Item item = itemMapper.toEntity(obterPorId(id));
        itemRepositorio.delete(item);
    }

    public UsuarioDto obterDono(Long id){
        ItemDto itemDto = obterPorId(id);
        return usuarioServico.obterPorId(itemDto.getUsuarioId());
    }

}