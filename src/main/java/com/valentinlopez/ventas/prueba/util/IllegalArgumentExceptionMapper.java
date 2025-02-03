package com.valentinlopez.ventas.prueba.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

//    @Override
//    public Response toResponse(IllegalArgumentException exception) {
//        return Response.status(Response.Status.BAD_REQUEST)
//                .entity(Map.of("error", exception.getMessage()))
//                .build();
//    }
@Override
public Response toResponse(IllegalArgumentException exception) {
    // Verifica si la excepción contiene múltiples errores (formato especial)
    if (exception instanceof ValidationException validationException) {
        Map<String, String> errors = validationException.getErrors();

        // Construye la respuesta en el formato requerido
        Map<String, Object> response = new HashMap<>();
        response.put("errors", errors);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }

    // Si es un solo error, devuelve el formato simple
    return Response.status(Response.Status.BAD_REQUEST)
            .entity(Map.of("error", exception.getMessage()))
            .build();
}
}
