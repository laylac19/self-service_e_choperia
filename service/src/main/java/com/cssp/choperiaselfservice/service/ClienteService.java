package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.domain.Cliente;
import com.cssp.choperiaselfservice.repository.ClienteRepository;
import com.cssp.choperiaselfservice.service.dto.ClienteDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteListDTO;
import com.cssp.choperiaselfservice.service.dto.ClienteSearchDTO;
import com.cssp.choperiaselfservice.service.mapper.ClienteMapper;
import com.cssp.choperiaselfservice.service.util.MensagemClienteUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public Cliente findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemClienteUtil.ENTITY_NOT_FOUND));
    }

    public Page<ClienteListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public List<ClienteListDTO> listCustomersWhoHaveEntered() {
        return repository.listCustomersWhoHaveEntered();
    }

    public ClienteDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public ClienteDTO save(ClienteDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Cliente cliente = findEntity(id);
        cliente.setAtivo(false);
        repository.save(cliente);
    }

    public ClienteSearchDTO findClienteByNumCartaoRFIDAndAndAtivoIsTrue(String barCode) {
        ClienteSearchDTO dto = repository.findClienteByNumCartaoRFIDLikeAndAtivoIsTrue(barCode);
        return dto;
    }

    public void customerEntry(ClienteDTO dto) {
        Cliente client = findEntity(dto.getId());
        client.setNumCartaoRFID(dto.getNumCartaoRFID());
        client.setAtivo(true);
        repository.save(client);
    }

    public void customerExit(Long id) {
        Cliente client = findEntity(id);
        client.setNumCartaoRFID(null);
        client.setAtivo(false);
        repository.save(client);
    }

    public void unlinkCardWithCustomer(Long idCLient) {
        Cliente client = findEntity(idCLient);
        client.setNumCartaoRFID(null);
        repository.save(client);
    }
}
