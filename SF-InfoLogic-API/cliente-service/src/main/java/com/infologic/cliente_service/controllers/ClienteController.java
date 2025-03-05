package com.infologic.cliente_service.controllers;

import com.infologic.cliente_service.entities.Cliente;
import com.infologic.cliente_service.services.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    //Evitar dependencias no inicializadas
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

}
