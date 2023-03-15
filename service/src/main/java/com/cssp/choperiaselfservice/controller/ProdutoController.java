package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.ProdutoService;
import com.cssp.choperiaselfservice.service.dto.ProdutoDTO;
import com.cssp.choperiaselfservice.service.dto.ProdutoListDTO;
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

@RestController
@RequestMapping("api/produto")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> findByID(@PathVariable("idProduto") Long idProduto) {
        return new ResponseEntity<>(service.findByID(idProduto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> delete(@PathVariable("idProduto") Long idProduto) {
        service.delete(idProduto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/entrada-produto/{idProduto}")
    public ResponseEntity<Void> enterProduct (@PathVariable("idProduto") Long idProduto, @RequestBody ProdutoDTO dto) {
        service.enterProduct(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/saida-produto/{idProduto}")
    public ResponseEntity<Void> releaseProductOutput (@PathVariable("idProduto") Long idProduto, @RequestBody ProdutoDTO dto) {
        service.productWithdrawal(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
