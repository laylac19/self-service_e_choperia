package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;

@RestController
@RequestMapping("api/relatorio")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping("/relatorio-estoque-produtos")
    public ResponseEntity<byte[]> balanceReportProductInStock() throws JRException, SQLException {
        byte[] pdf = service.balanceReportProductInStock();

        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "relatorio-estoque-produtos.pdf");

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
    @GetMapping("/relatorio-ponto-encomenda-produtos")
    public ResponseEntity<byte[]> pointOfOrderProductReport() throws JRException, SQLException {
        byte[] pdf = service.pointOfOrderProductReport();

        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "relatorio-ponto-encomenda-produtos.pdf");

        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @GetMapping("/relatorio-chopes-populares")
    public ResponseEntity<byte[]> reportMostConsumedBeersInAPeriod(@RequestParam("dataInicio") Date dataInicio,
                                                                   @RequestParam("dataFinal") Date dataFinal) throws JRException, SQLException {
        byte[] pdf = service.reportMostConsumedBeersInAPeriod(dataInicio, dataFinal);

        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "relatorio-chopes-populares.pdf");

        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @GetMapping("/relatorio-total-compras-clientes")
    public ResponseEntity<byte[]> customerReportWithAmountPurchasedInPeriod(@RequestParam("dataInicio") Date dataInicio,
                                                                            @RequestParam("dataFinal") Date dataFinal) throws JRException, SQLException {
        byte[] pdf = service.customerReportWithAmountPurchasedInPeriod(dataInicio, dataFinal);

        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "relatorio-total-compras-clientes.pdf");

        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @GetMapping("/relatorio-receita-despesas")
    public ResponseEntity<byte[]> expenseAndIncomeMovementReport(@RequestParam("dataInicio") Date dataInicio,
                                                                            @RequestParam("dataFinal") Date dataFinal) throws JRException, SQLException {
        byte[] pdf = service.expenseAndIncomeMovementReport(dataInicio, dataFinal);

        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "relatorio-receita-despesas.pdf");

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
