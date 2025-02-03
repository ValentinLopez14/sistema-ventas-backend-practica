package com.valentinlopez.ventas.prueba.cilentes.resource.impl;

import com.valentinlopez.ventas.prueba.cilentes.model.ClienteRequest;
import com.valentinlopez.ventas.prueba.cilentes.model.projection.ClienteResponse;
import com.valentinlopez.ventas.prueba.cilentes.resource.ClienteResource;
import com.valentinlopez.ventas.prueba.cilentes.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

@Slf4j
public class ClientesResourceImpl implements ClienteResource {

    @Inject
    private ClienteService clienteService;

    @Override
    public Response listarClientes() {

        // Obtén la lista de clientes desde el servicio
        List<ClienteResponse> clientes = clienteService.listarClientes();

        var response = new HashMap<String, Object>();

        response.put("data", clienteService.listarClientes());
        response.put("count", clientes.size()); // Agrega el contador de elementos


        return Response.ok(response).build();
    }

    @Override
    public Response obtenerClientePorId(Integer id) {
        // Obtén el cliente desde el servicio
        ClienteResponse cliente = clienteService.obtenerClientePorId(id);

        // Prepara la respuesta con el cliente encapsulado en "data"
        var response = new HashMap<String, Object>();
        response.put("data", cliente); // Agrega el cliente bajo la clave "data"

        // Devuelve la respuesta como un objeto Response
        return Response.ok(response).build();
    }

//    @Override
//    public Response registrarCliente(ClienteRequest clienteRequest) {
//        ClienteResponse cliente = clienteService.crearCliente(clienteRequest);
//        return Response.status(Response.Status.CREATED).entity(cliente).build();
//    }

    @Override
    public Response registrarCliente(@Valid ClienteRequest clienteRequest) {
        var response = new HashMap<String, Object>();

        // Aquí puedes llamar al servicio para registrar el cliente si pasa las validaciones
        ClienteResponse cliente = clienteService.crearCliente(clienteRequest);
        response.put("data", cliente);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @Override
    public Response actualizarCliente(Integer id, ClienteRequest clienteRequest) {
        ClienteResponse clienteActualizado = clienteService.actualizarCliente(id, clienteRequest);
        return Response.ok(clienteActualizado).build();
    }

    @Override
    public Response eliminarCliente(Integer id) {
        clienteService.eliminarCliente(id);
        return Response.noContent().build();
    }

}
