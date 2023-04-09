package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.CozinhaService;
import com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoDTO;
import com.cssp.choperiaselfservice.service.dto.NotificacaoSelfServicePratoListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cozinha")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class CozinhaController {
    private final CozinhaService service;

    @GetMapping
    public ResponseEntity<List<NotificacaoSelfServicePratoListDTO>> findAll(Pageable pageable) {
        return new ResponseEntity<>(service.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idNotificacao}")
    public ResponseEntity<NotificacaoSelfServicePratoDTO> findByID(@PathVariable("idNotificacao") Long idNotificacao) {
        return new ResponseEntity<>(service.findByID(idNotificacao), HttpStatus.OK);
    }

    @DeleteMapping("/{idNotificacao}")
    public ResponseEntity<Void> delete(@PathVariable("idNotificacao") Long idNotificacao) {
        service.delete(idNotificacao);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/solicitar-reposicao")
    public ResponseEntity<Void> save(String prato) {
        service.needToReplacePlate(prato);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/repor-prato")
    public ResponseEntity<Void> save(Long idPrato) {
        service.replacePlate(idPrato);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
