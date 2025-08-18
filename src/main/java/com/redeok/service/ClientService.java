package com.redeok.service;

import com.redeok.model.Client;
import com.redeok.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    // ------- BOAS PRÁTICAS OBSERVÁVEIS -------
    // 1. Nomes de métodos claros e autoexplicativos
    // 2. Tratamento de erros específicos
    // 3. Validações antes de operações
    // 4. Logs estratégicos (adicionar depois)

    @Transactional
    public Client registerNewClient(Client client) {
        // Validação customizada (não depende apenas das anotações)
        if (client.getDocument() == null || client.getDocument().isBlank()) {
            throw new IllegalArgumentException("Documento é obrigatório");
        }

        // Verifica duplicidade (lógica de negócio)
        if (clientRepository.existsByDocument(client.getDocument())) {
            throw new IllegalStateException("Já existe um cliente com este documento");
        }

        // Complementa dados automaticamente
        client.setCreatedAt(LocalDateTime.now());
        client.setActive(true);

        return clientRepository.save(client);
    }

    // Paginação implementada manualmente (não só delegada ao repositório)
    public List<Client> searchClients(String nameFilter, int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Parâmetros de paginação inválidos");
        }

        return clientRepository.findByNameContaining(
            nameFilter, 
            page * pageSize, 
            pageSize
        );
    }

    // Atualização parcial com merge manual
    @Transactional
    public Client updateClientDetails(Long clientId, Client partialUpdate) {
        Client existing = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualiza apenas os campos não nulos (PATCH)
        if (partialUpdate.getName() != null) {
            existing.setName(partialUpdate.getName());
        }
        if (partialUpdate.getEmail() != null) {
            existing.setEmail(partialUpdate.getEmail());
        }

        return clientRepository.save(existing);
    }
}