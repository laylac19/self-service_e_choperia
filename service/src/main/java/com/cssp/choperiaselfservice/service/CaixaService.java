package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Caixa;
import com.cssp.choperiaselfservice.domain.Cliente;
import com.cssp.choperiaselfservice.domain.CompraCaixa;
import com.cssp.choperiaselfservice.repository.CaixaRepository;
import com.cssp.choperiaselfservice.repository.CompraCaixaRepository;
import com.cssp.choperiaselfservice.service.dto.*;
import com.cssp.choperiaselfservice.service.mapper.CaixaMapper;
import com.cssp.choperiaselfservice.service.util.CaixaUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CaixaService {

    private final CaixaRepository repository;
    private final CompraCaixaRepository compraCaixaRepository;

    private final CaixaMapper mapper;

    private final ClienteCompraProdutoService service;
    private final ClienteService customerService;

    private Caixa findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(CaixaUtil.ENTITY_NOT_FOUND));
    }

    public CaixaDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }


//    public CaixaDTO save(CaixaOtimizadoDTO dto) {
//        Cliente clienteResponsavel = customerService.findEntity(dto.getIdClienteResponsavelCompra());
//        Double totalCompra = 0.0;
//        totalCompra += clienteResponsavel
//                .getClienteCompras()
//                .stream()
//                .filter(clienteCompra -> clienteCompra.getAtivo())
//                .mapToDouble(clienteCompra -> clienteCompra.getValorCompra())
//                .reduce(0, (total, valorCompra) -> total + valorCompra);
//
//        for(int i = 0; i<dto.getIdClientesConjuntos().size(); i++){
//            Cliente cliente = customerService.findEntity(dto.getIdClientesConjuntos().get(i));
//            Double totalCompraCliente = cliente.getClienteCompras()
//                    .stream()
//                    .filter(clienteCompra -> clienteCompra.getAtivo())
//                    .mapToDouble(clienteCompra -> clienteCompra.getValorCompra())
//                    .reduce(0, (total, valorCompra) -> total + valorCompra);
//            totalCompra += totalCompraCliente;
//        }
//
//        Caixa caixa = new Caixa();
//        caixa.setDesconto(0.0);
//        caixa.setAtivo(true);
//        caixa.setTotalConta(totalCompra);
//        caixa.setFormaPagamento(FormaPagamento.DINHEIRO);
//        caixa.setValorFinal(totalCompra);
//        //caixa.setClientes();
//        return mapper.toDto(repository.save(caixa));
//    }

    public CaixaDTO save(CaixaDTO dto) {
        List rfidClientes = dto.getListCodRfid();
        CaixaDTO caixa = mapper.toDto(repository.save(mapper.toEntity(dto)));
        //Set<ComprasCaixaListDTO> listaCompras = null;
        for(int i = 0; i<rfidClientes.size(); i++){
            this.service.listPurchasedItemsOfCustomer(rfidClientes.get(i).toString()).forEach(iten -> {
                CompraCaixa compra = new CompraCaixa();
                compra.setIdCaixa(caixa.getId());
                compra.setIdCompra(iten.getIdCompra());
                //  listaCompras.add(iten);
                this.compraCaixaRepository.save(compra);
            });
        }
        //chamar func de finalizar
        setCodRfidNull(rfidClientes);
        //service.setFalseCompraProduto(listaCompras);
        return caixa;
    }

    public void delete(Long id) {
        Caixa cashier = findEntity(id);
        cashier.setAtivo(false);
        repository.save(cashier);
    }

    private void setCodRfidNull(List<String> rfidClientes){
        rfidClientes.forEach(rfid -> {
            ClienteSearchDTO cliente = this.customerService.findClienteByNumCartaoRFIDAndAndAtivoIsTrue(rfid);
            ClienteDTO clienteDto = this.customerService.findByID(cliente.getId());
            customerService.customerExit(clienteDto.getId());
        });
    }

}
