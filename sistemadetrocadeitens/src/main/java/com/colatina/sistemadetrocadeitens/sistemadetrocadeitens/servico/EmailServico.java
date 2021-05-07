package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.configuracao.ApplicationProperties;
import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailServico {

    private final JavaMailSender javaMailSender;
    private final ApplicationProperties applicationProperties;

    public void sendMail(EmailDto emailDto){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            message.setFrom(applicationProperties.getEnderecoRemetente(),
                    applicationProperties.getNomeRemetente());
            message.setTo(emailDto.getDestinatario());
            message.setSubject(emailDto.getAssunto());
            for(String s : emailDto.getCopias()){
                message.addCc(s);
            }
            message.setText(emailDto.getTexto(), true);
            javaMailSender.send(mimeMessage);
        }catch(MessagingException | UnsupportedEncodingException e){
            throw new RuntimeException("ERRO AO ENVIAR E-MAIL");
        }
    }
}