package com.programandoenjava.desafiomarzo2024.service;

import com.programandoenjava.desafiomarzo2024.entities.Cliente;
import com.programandoenjava.desafiomarzo2024.repository.IClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public String crearCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return "Se ha creado un cliente";
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

}
