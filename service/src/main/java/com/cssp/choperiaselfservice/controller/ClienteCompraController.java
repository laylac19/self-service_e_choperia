package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.ClienteCompraService;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraDTO;
import com.cssp.choperiaselfservice.service.dto.ComprasCaixaListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class ClienteCompraController {
    private final ClienteCompraService service;


    @GetMapping("/{idCompra}")
    public ResponseEntity<ClienteCompraDTO> findByID(@PathVariable("idCompra") Long idCompra) {
        return new ResponseEntity<>(service.findByID(idCompra), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteCompraDTO> save(@RequestBody ClienteCompraDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCompra}")
    public ResponseEntity<Void> delete(@PathVariable("idCompra") Long idCompra) {
        service.delete(idCompra);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/compras-cliente/{cartaoRFID}")
    public ResponseEntity<Set<ComprasCaixaListDTO>> findByRFID(@PathVariable("cartaoRFID") String cartaoRFID) {
        return new ResponseEntity<>(service.listPurchasedItemsOfCustomer(cartaoRFID), HttpStatus.OK);
    }
}
