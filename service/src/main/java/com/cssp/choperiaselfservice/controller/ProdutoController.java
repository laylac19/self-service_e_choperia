package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.ProdutoService;
import com.cssp.choperiaselfservice.service.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/produto")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;

    @PutMapping("/entrada-produtos")
    public ResponseEntity<Void> enterProduct (@RequestBody Set<ProdutoDTO> dtoSet) {
        service.enterListOfProducts(dtoSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/saida-produtos")
    public ResponseEntity<Void> releaseProductOutput (@RequestBody Set<ProdutoDTO> dtoSet) {
        service.withdrawalListOfProducts(dtoSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
