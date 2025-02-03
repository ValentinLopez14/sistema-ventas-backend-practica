package com.valentinlopez.ventas.prueba.cilentes.service;

import com.valentinlopez.ventas.prueba.cilentes.model.ClienteEntity;
import com.valentinlopez.ventas.prueba.cilentes.model.ClienteRequest;
import com.valentinlopez.ventas.prueba.cilentes.model.projection.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse crearCliente(ClienteRequest clienteRequest);
    List<ClienteResponse> listarClientes();
    ClienteResponse obtenerClientePorId(Integer id);
    ClienteResponse actualizarCliente(Integer id, ClienteRequest clienteRequest);
    void eliminarCliente(Integer id);
}
