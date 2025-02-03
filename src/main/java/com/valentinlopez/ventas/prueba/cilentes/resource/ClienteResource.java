package com.valentinlopez.ventas.prueba.cilentes.resource;

import com.valentinlopez.ventas.prueba.cilentes.model.ClienteRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ClienteResource {

    @GET
    Response listarClientes();

    @GET
    @Path("/{id}")
    Response obtenerClientePorId(@PathParam("id") Integer id);

    @POST
    Response registrarCliente(@Valid ClienteRequest clienteRequest);

    @PUT
    @Path("/{id}")
    Response actualizarCliente(@PathParam("id") Integer id, @Valid ClienteRequest clienteRequest);

    @DELETE
    @Path("/{id}")
    Response eliminarCliente(@PathParam("id") Integer id);
}
