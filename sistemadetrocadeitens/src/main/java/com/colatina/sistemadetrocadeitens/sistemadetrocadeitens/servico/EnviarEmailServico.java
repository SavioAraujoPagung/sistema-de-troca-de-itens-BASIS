package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.EmailDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.OfertaDto;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EnviarEmailServico {

    private final EmailServico emailServico;
    private final ItemServico itemServico;
    private final UsuarioServico usuarioServico;

    private void enviarEmail(EmailDto emailDto) { emailServico.sendMail(emailDto); }

    public void enviarEmailNovaOferta(OfertaDto ofertaDto){
        UsuarioDto usuarioDtoDisponivel = itemServico.obterDono(ofertaDto.getItemId());
        UsuarioDto usuarioDtoOfertante = usuarioServico.obterPorId(ofertaDto.getUsuarioOfertanteId());

        EmailDto emailDto = new EmailDto();
        emailDto.setAssunto("UM DE SEUS PRODUTOS RECEBEU UMA NOVA OFERTA!");
        emailDto.setDestinatario(usuarioDtoDisponivel.getEmail());

        emailDto.setTexto("O senhor(a) " + usuarioDtoOfertante.getNome() +
                " ofereceu "+ ofertaDto.getItensOfertados().size() +
                " produto(s) pelo seu produto: " + itemServico.obterPorId(ofertaDto.getItemId()).getNome());

        enviarEmail(emailDto);
    }

    public void enviarEmailOfertaAceita(OfertaDto ofertaDto){
        UsuarioDto usuarioDtoDisponivel = itemServico.obterDono(ofertaDto.getItemId());
        UsuarioDto usuarioDtoOfertante = usuarioServico.obterPorId(ofertaDto.getUsuarioOfertanteId());

        EmailDto emailDto = new EmailDto();
        emailDto.setAssunto("UMA DE SUAS OFERTAS FOI ACEITA!");
        emailDto.setDestinatario(usuarioDtoOfertante.getEmail());

        emailDto.setTexto("O senhor(a) " + usuarioDtoDisponivel.getNome() +
                " ACEITOU a sua oferta feita pelo(a) " + itemServico.obterPorId(ofertaDto.getItemId()).getNome() +
                " que voce tanto queria." +
                "\n\n\tAVISO: todas as outras ofertas de troca envolvendo qualquer um dos itens que você ofereceu" +
                " (tenham elas sido feitas desejando seu item ou você oferendo-o para em outra troca) foram automaticamente canceladas." +
                " Essa operaçao não pode ser revertida (pelo menos não diretamente).");

        enviarEmail(emailDto);
    }

    public void enviarEmailOfertaCancelada(OfertaDto ofertaDto){
        UsuarioDto usuarioDtoDisponivel = itemServico.obterDono(ofertaDto.getItemId());
        UsuarioDto usuarioDtoOfertante = usuarioServico.obterPorId(ofertaDto.getUsuarioOfertanteId());

        EmailDto emailDto = new EmailDto();
        emailDto.setAssunto("UMA DE SUAS OFERTAS FOI CANCELADA!");
        emailDto.setDestinatario(usuarioDtoOfertante.getEmail());

        emailDto.setTexto("A oferta que você fez pelo(a) " + itemServico.obterPorId(ofertaDto.getItemId()).getNome() +
                " do(a) senhor(a) " + usuarioDtoDisponivel.getNome() +
                " foi CANCELADA." +
                "\nCaso não tenha sido você mesmo não tenha pedido o cancelamento," +
                " é provavel que um dos itens envolvidas na oferta tenha sido trocado em outra oferta.");

        enviarEmail(emailDto);
    }

    public void enviarEmailOfertaRecusada(OfertaDto ofertaDto){
        UsuarioDto usuarioDtoDisponivel = itemServico.obterDono(ofertaDto.getItemId());
        UsuarioDto usuarioDtoOfertante = usuarioServico.obterPorId(ofertaDto.getUsuarioOfertanteId());

        EmailDto emailDto = new EmailDto();
        emailDto.setAssunto("UMA DE SUAS OFERTAS FOI RECUSADA!");
        emailDto.setDestinatario(usuarioDtoOfertante.getEmail());

        emailDto.setTexto("Lamentamos " + usuarioDtoOfertante.getNome() +
                ", mas o(a) senhor(a) " + usuarioDtoDisponivel.getNome() +
                " RECUSOU a sua oferta feita pelo " + itemServico.obterPorId(ofertaDto.getItemId()).getNome() +
                " que voce tanto queria. Uma pena.");

        enviarEmail(emailDto);
    }

}