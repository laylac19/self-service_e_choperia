package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.ClienteService;
import com.cssp.choperiaselfservice.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cliente")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    public ResponseEntity<Page<ClienteListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/entradas")
    public ResponseEntity<List<ClienteListDTO>> listCustomersWhoHaveEntered() {
        return new ResponseEntity<>(service.listCustomersWhoHaveEntered(), HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> findByID(@PathVariable("idCliente") Long idCliente) {
        return new ResponseEntity<>(service.findByID(idCliente), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> save(@RequestBody ClienteDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> delete(@PathVariable("idCliente") Long idCliente) {
        service.delete(idCliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar-cliente/{cod}")
    public ResponseEntity<ClienteSearchDTO> findClienteByNumCartaoRFIDAndAndAtivoIsTrue(@PathVariable("cod") String cod) {
        return new ResponseEntity<>(service.findClienteByNumCartaoRFIDAndAndAtivoIsTrue(cod), HttpStatus.OK);
    }

    @PostMapping("/resgistrar-entrada-cliente")
    public ResponseEntity<Void> customerEntry(@RequestBody ClienteDTO dto) {
        service.customerEntry(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/resgistrar-saida-cliente/{idCliente}")
    public ResponseEntity<Void> customerExit(@PathVariable("idCliente") Long idCliente) {
        service.customerExit(idCliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/relatorio-total-compras-clientes")
    public ResponseEntity<List<ClienteRelatorioDTO>> customerReportWithAmountPurchasedInPeriod(@RequestBody RelatorioEntreDatasDTO report) {
        return new ResponseEntity<>(service.customerReportWithAmountPurchasedInPeriod(report), HttpStatus.CREATED);
    }

    @GetMapping("/{cardRFID}/totalConta")
    public ResponseEntity<Double> checkCustomerCardBalance(@PathVariable("cardRFID") String cardRFID) {
        return new ResponseEntity<>(service.checkCustomerCardBalance(cardRFID), HttpStatus.OK);
    }

}
