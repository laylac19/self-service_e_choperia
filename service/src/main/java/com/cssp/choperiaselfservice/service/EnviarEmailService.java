package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.enums.DiretoriaExecutiva;
import com.cssp.choperiaselfservice.service.dto.BuildEmailPurchadeDTO;
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
        SimpleMailMessage message = new SimpleMailMessage();
        BuildEmailPurchadeDTO build = emailDTO.getBuild();
        message.setTo(DiretoriaExecutiva.findEmails());
        message.setSubject("CSSP Compras");
        message.setText("Olá " + build.getNomeCliente() + "," +
                "\n\n" + emailDTO.getMensagem() +
                "\n\n Data Compra: " + build.getDataCompra() + " - Valor Compra: " + build.getValorCompra() +
                "\n\n\n\n Obrigada pela prefeência!!!");
        envioEmailJava.send(message);
        log.info("Enviado");
    }

//    public void purchasesMade(EmailClienteCompraDTO emailDTO) {
//        log.info("Enviando Email");
//        SimpleMailMessage message = new SimpleMailMessage();
//        BuildEmailPurchadeDTO build = emailDTO.getBuild();
//        message.setTo(build.getEmailCliente());
//        message.setSubject("CSSP Compras");
//        message.setText("Olá " + build.getNomeCliente() + "," +
//                "\n\n" + emailDTO.getMensagem() +
//                "\n\n Data Compra: " + build.getDataCompra() + " - Valor Compra: " + build.getValorCompra() +
//                "\n\n\n\n Obrigada pela prefeência!!!");
//        envioEmailJava.send(message);
//        log.info("Enviado");
//    }
}
