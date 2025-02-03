package com.valentinlopez.ventas.prueba.cilentes.repository;

import com.valentinlopez.ventas.prueba.cilentes.model.ClienteEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepositoryBase<ClienteEntity, Integer> {


}
