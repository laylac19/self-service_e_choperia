package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.CaixaService;
import com.cssp.choperiaselfservice.service.dto.CaixaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/caixa")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class CaixaController {
    private final CaixaService service;

    @GetMapping("/{idCaixa}")
    public ResponseEntity<CaixaDTO> findByID(@PathVariable("idCaixa") Long idCaixa) {
        return new ResponseEntity<>(service.findByID(idCaixa), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<CaixaDTO> save(@RequestBody CaixaOtimizadoDTO dto) {
//        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
//    }

    @DeleteMapping("/{idCaixa}")
    public ResponseEntity<Void> delete(@PathVariable("idCaixa") Long idCaixa) {
        service.delete(idCaixa);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/liberar-cartao")
//    public ResponseEntity<Void> removerVinculoRfidCliente(@RequestBody CaixaDTO dto) {
//        service.finalizeSale(dto);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
