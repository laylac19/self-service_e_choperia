package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.enums.DiretoriaExecutiva;
import com.cssp.choperiaselfservice.service.dto.EmailClienteCompraDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnviarEmailService {
    private final JavaMailSender envioEmailJava;

    public void purchasesMade(EmailClienteCompraDTO emailDTO) {
        log.info("Enviando Email");
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(DiretoriaExecutiva.findEmails());
        mensagem.setSubject("CSSP Compras");
        mensagem.setText("Olá " + emailDTO.getNomeCliente() +
                "\n\n" + emailDTO.getMensagem() +
                "\n\n\n Consulta: " + emailDTO.getDtInicioPeriodo() + " - " + emailDTO.getDtFimPeriodo());
        envioEmailJava.send(mensagem);
        log.info("Enviado");
    }
}
