package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.ChopeCompraService;
import com.cssp.choperiaselfservice.service.dto.ChopeCompraDTO;
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

@RestController
@RequestMapping("api/chope-compra")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ChopeCompraController {
    private final ChopeCompraService service;

    @GetMapping("/{idCompra}")
    public ResponseEntity<ChopeCompraDTO> findByID(@PathVariable("idCompra") Long idCompra) {
        return new ResponseEntity<>(service.findByID(idCompra), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChopeCompraDTO> save(@RequestBody ChopeCompraDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCompra}")
    public ResponseEntity<Void> delete(@PathVariable("idCompra") Long idCompra) {
        service.delete(idCompra);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
