package com.redeok.repository;

import com.redeok.model.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client> {

    // Busca cliente por ID
    public Optional<Client> findByIdOptional(Long id) {
        return find("id", id).firstResultOptional();
    }

    // Delete seguro
    public boolean deleteByIdSafe(Long id) {
        return delete("id", id) > 0;
    }
}
