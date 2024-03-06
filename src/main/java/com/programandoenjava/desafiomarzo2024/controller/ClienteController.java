package com.programandoenjava.desafiomarzo2024.controller;

import com.programandoenjava.desafiomarzo2024.entities.Cliente;
import com.programandoenjava.desafiomarzo2024.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cliente")
public class ClienteController {
     private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/crear")
    public String crearCliente (@RequestBody Cliente cliente){
       return clienteService.crearCliente(cliente);
    }

    @GetMapping("/lista")
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }
}

