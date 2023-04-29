package com.cssp.choperiaselfservice.controller;

import com.cssp.choperiaselfservice.service.ProdutoService;
import com.cssp.choperiaselfservice.service.dto.EntradaProdutoDTO;
import com.cssp.choperiaselfservice.service.dto.InsumoListDTO;
import com.cssp.choperiaselfservice.service.dto.ProdutoDTO;
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
@RequestMapping("api/produto")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> findByID(@PathVariable("idProduto") Long idProduto) {
        return new ResponseEntity<>(service.findByID(idProduto), HttpStatus.OK);
    }

    @GetMapping("/buscar-cod-barras/{codigoBarras}")
    public ResponseEntity<InsumoListDTO> findInputByBarCode(@PathVariable("codigoBarras") String codigoBarras) {
        return new ResponseEntity<>(service.findInputByBarCode(codigoBarras), HttpStatus.OK);
    }

    @GetMapping("/buscar-self-service")
    public ResponseEntity<ProdutoDTO> findSelfServiceProduct() {
        return new ResponseEntity<>(service.findSelfServiceProduct(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> delete(@PathVariable("idProduto") Long idCliente) {
        service.delete(idCliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/entrada-produto")
    public ResponseEntity<Void> enterListOfProducts(@RequestBody Set<EntradaProdutoDTO> productDTOList) {
        service.enterListOfProducts(productDTOList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/saida-produto")
    public ResponseEntity<Void> withdrawalListOfProducts(@RequestBody Set<EntradaProdutoDTO> productDTOList) {
        service.withdrawalListOfProducts(productDTOList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listar-insumos")
    public ResponseEntity<Page<InsumoListDTO>> listAllInputs(Pageable pageable) {
        return new ResponseEntity<>(service.listAllInputs(pageable), HttpStatus.OK);
    }
}
