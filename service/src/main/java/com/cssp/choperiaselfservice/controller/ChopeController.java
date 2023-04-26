package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.ChopeService;
import com.cssp.choperiaselfservice.service.dto.ChopeListDTO;
import com.cssp.choperiaselfservice.service.dto.ChopeViewDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteCompraProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/chope")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ChopeController {
    private final ChopeService service;

    @GetMapping
    public ResponseEntity<Page<ChopeListDTO>> listAll(Pageable pageable) {
        return new ResponseEntity<>(service.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/listar-para-cliente")
    public ResponseEntity<List<ChopeViewDTO>> listAllDraftBeers() {
        return new ResponseEntity<>(service.listAllDraftBeers(), HttpStatus.OK);
    }

    @PutMapping("/saida-produto/{idChope}")
    public ResponseEntity<Void> releaseProductOutput(@PathVariable("idChope") Long idChope) {
        service.withdrawalMovementInChoppStock(idChope);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cliente-chope-saida")
    public ResponseEntity<Void> removalBeerMugByTheCustomer(@RequestBody ClienteCompraProdutoDTO dto) {
        service.removalBeerMugByTheCustomer(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
