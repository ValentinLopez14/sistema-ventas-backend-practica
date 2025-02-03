package com.valentinlopez.ventas.prueba.cilentes.model.projection;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteResponse(

        Integer clienteId,
        String nombre,
        String correo,
        String telefono,
        String direccion,
        LocalDateTime fechaRegistro

) { }
