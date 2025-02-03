package com.valentinlopez.ventas.prueba.cilentes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "clientes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, length = 15)
    private String telefono;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
