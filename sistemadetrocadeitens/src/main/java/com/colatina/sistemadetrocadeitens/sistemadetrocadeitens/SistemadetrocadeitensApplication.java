package com.colatina.sistemadetrocadeitens.sistemadetrocadeitens;

import com.colatina.sistemadetrocadeitens.sistemadetrocadeitens.servico.EmailServico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemadetrocadeitensApplication {

	public static void main(String[] args) {
		EmailServico teste = new EmailServico(mailSender);
		teste.sendMail();
		SpringApplication.run(SistemadetrocadeitensApplication.class, args);
	}

}
