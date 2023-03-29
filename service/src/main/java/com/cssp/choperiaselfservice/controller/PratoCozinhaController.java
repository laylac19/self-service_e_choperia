package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.PratoCozinhaService;
import com.cssp.choperiaselfservice.service.dto.PratoDTO;
import com.cssp.choperiaselfservice.service.dto.PratoListDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/prato-produto")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class PratoCozinhaController {
    private final PratoCozinhaService service;

    @GetMapping
    public ResponseEntity<Page<PratoListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<PratoDTO> findByID(@PathVariable("idProduto") Long idProduto) {
        return new ResponseEntity<>(service.findByID(idProduto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PratoDTO> save(@RequestBody PratoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> delete(@PathVariable("idProduto") Long idProduto) {
        service.delete(idProduto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
