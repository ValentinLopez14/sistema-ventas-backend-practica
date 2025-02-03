package com.valentinlopez.ventas.prueba.cilentes.service.impl;

import com.valentinlopez.ventas.prueba.cilentes.model.ClienteEntity;
import com.valentinlopez.ventas.prueba.cilentes.model.ClienteRequest;
import com.valentinlopez.ventas.prueba.cilentes.model.projection.ClienteResponse;
import com.valentinlopez.ventas.prueba.cilentes.repository.ClienteRepository;
import com.valentinlopez.ventas.prueba.cilentes.service.ClienteService;
import com.valentinlopez.ventas.prueba.util.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public ClienteResponse crearCliente(ClienteRequest clienteRequest) {
        Map<String, String> errors = new HashMap<>();

        // Realiza las validaciones manuales y agrega errores al mapa
        if (clienteRequest.getNombre() == null || clienteRequest.getNombre().isBlank()) {
            errors.put("nombre", "El nombre no puede estar vacío");
        }
        if (clienteRequest.getCorreo() == null || clienteRequest.getCorreo().isBlank()) {
            errors.put("correo", "El correo no puede estar vacío");
        } else if (!clienteRequest.getCorreo().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            errors.put("correo", "El correo debe tener un formato válido");
        }
        if (clienteRequest.getTelefono() == null || !clienteRequest.getTelefono().matches("\\d{9,15}")) {
            errors.put("telefono", "El teléfono debe contener entre 9 y 15 dígitos");
        }
        if (clienteRequest.getDireccion() == null || clienteRequest.getDireccion().isBlank()) {
            errors.put("direccion", "La dirección no puede estar vacía");
        }

        // Si hay errores, lanza la excepción personalizada
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        // Si no hay errores, crea y persiste el cliente
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setCorreo(clienteRequest.getCorreo());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setDireccion(clienteRequest.getDireccion());
        clienteRepository.persist(cliente);

        return new ClienteResponse(
                cliente.getClienteId(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getFechaRegistro()
        );
    }

    @Override
    public List<ClienteResponse> listarClientes() {
        // Convertir directamente usando un record
        return clienteRepository.listAll()
                .stream()
                .map(cliente -> new ClienteResponse(
                        cliente.getClienteId(),
                        cliente.getNombre(),
                        cliente.getCorreo(),
                        cliente.getTelefono(),
                        cliente.getDireccion(),
                        cliente.getFechaRegistro()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse obtenerClientePorId(Integer id) {
        ClienteEntity cliente = clienteRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));

        // Crear directamente ClienteResponse
        return new ClienteResponse(
                cliente.getClienteId(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getFechaRegistro()
        );
    }

    @Override
    @Transactional
    public ClienteResponse actualizarCliente(Integer id, ClienteRequest clienteRequest) {
        ClienteEntity cliente = clienteRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));

        cliente.setNombre(clienteRequest.getNombre());
        cliente.setCorreo(clienteRequest.getCorreo());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setDireccion(clienteRequest.getDireccion());
        clienteRepository.persist(cliente);

        // Crear directamente ClienteResponse
        return new ClienteResponse(
                cliente.getClienteId(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getFechaRegistro()
        );
    }

    @Override
    @Transactional
    public void eliminarCliente(Integer id) {
        ClienteEntity cliente = clienteRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
        clienteRepository.delete(cliente);
    }
}
