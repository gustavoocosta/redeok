package com.redeok.service;

import com.redeok.model.Client;
import com.redeok.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    @Transactional
    public Client registerNewClient(Client client) {
        if (client.getDocument() == null || client.getDocument().isBlank()) {
            throw new IllegalArgumentException("Documento é obrigatório");
        }

        // Verifica duplicidade usando Panache
        if (clientRepository.find("document", client.getDocument()).count() > 0) {
            throw new IllegalStateException("Já existe um cliente com este documento");
        }

        clientRepository.persist(client);
        return client;
    }

    public List<Client> searchClients(String nameFilter, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Parâmetros de paginação inválidos");
        }

        if (nameFilter != null && !nameFilter.isEmpty()) {
            return clientRepository.find("name like ?1", "%" + nameFilter + "%")
                    .page(page, pageSize)
                    .list();
        }
        
        return clientRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    @Transactional
    public Client updateClientDetails(Long clientId, Client partialUpdate) {
        Client existing = clientRepository.findByIdOptional(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (partialUpdate.getName() != null) {
            existing.setName(partialUpdate.getName());
        }
        if (partialUpdate.getEmail() != null) {
            existing.setEmail(partialUpdate.getEmail());
        }

        return existing;
    }
}