package com.redeok.repository;

import com.redeok.model.Address;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    // Lista todos endereços de um cliente
    public List<Address> findByClientId(Long clientId) {
        return list("client.id", clientId);
    }

    // Busca específica (garante que o endereço pertence ao cliente)
    public Optional<Address> findByIdAndClientId(Long id, Long clientId) {
        return find("id = ?1 and client.id = ?2", id, clientId).firstResultOptional();
    }

    // Delete seguro (só deleta se pertencer ao cliente)
    public boolean deleteByIdAndClientId(Long id, Long clientId) {
        return delete("id = ?1 and client.id = ?2", id, clientId) > 0;
    }
}