package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.ClienteCompraProdutoService;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraProdutoDTO;
import com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/cliente-compra")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ClienteCompraProdutoController {
    private final ClienteCompraProdutoService service;

    @GetMapping("/compras-cliente/{cartaoRFID}")
    public ResponseEntity<Set<ComprasCaixaListDTO>> listPurchasedItemsOfCustomer(@PathVariable("cartaoRFID") String cartaoRFID) {
        return new ResponseEntity<>(service.listPurchasedItemsOfCustomer(cartaoRFID), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteCompraProdutoDTO> save(@RequestBody ClienteCompraProdutoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

}
