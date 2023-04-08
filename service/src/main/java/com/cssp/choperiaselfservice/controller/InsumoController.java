package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.InsumoService;
import com.cssp.choperiaselfservice.service.dto.InsumoDTO;
import com.cssp.choperiaselfservice.service.dto.InsumoListDTO;
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

import java.util.Set;

@RestController
@RequestMapping("api/insumo")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class InsumoController {
    private final InsumoService service;

    @GetMapping
    public ResponseEntity<Page<InsumoListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idInsumo}")
    public ResponseEntity<InsumoDTO> findByID(@PathVariable("idInsumo") Long idInsumo) {
        return new ResponseEntity<>(service.findByID(idInsumo), HttpStatus.OK);
    }

    @GetMapping("/buscar-cod-barras/{barCode}")
    public ResponseEntity<InsumoListDTO> findInputByBarCode(@PathVariable("barCode") String barCode) {
        return new ResponseEntity<>(service.findInputByBarCode(barCode), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InsumoDTO> save(@RequestBody InsumoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idInsumo}")
    public ResponseEntity<Void> delete(@PathVariable("idInsumo") Long idInsumo) {
        service.delete(idInsumo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/entrada-produto")
    public ResponseEntity<Void> enterProduct(@RequestBody Set<InsumoDTO> dtoSet) {
        service.enterListOfProducts(dtoSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/saida-produto")
    public ResponseEntity<Void> releaseProductOutput(@RequestBody Set<InsumoDTO> dtoSet) {
        service.withdrawalListOfProducts(dtoSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
