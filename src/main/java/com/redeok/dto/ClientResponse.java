package com.redeok.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.redeok.model.Client;

import java.time.LocalDateTime;

public class ClientResponse {
    private Long id;
    private String name;
    private String formattedPhone;
    private String email;
    private String formattedDocument;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    // Construtor que encapsula a formatação
    public ClientResponse(Client client) {
        this.id = client.getId();
        this.name = capitalizeName(client.getName());
        this.formattedPhone = formatPhone(client.getPhone());
        this.email = client.getEmail();
        this.formattedDocument = formatDocument(
            client.getDocument(), 
            client.getDocumentType()
        );
        this.createdAt = client.getCreatedAt();
    }

    // ---- Métodos de Formatação (Toque Humano) ----
    private String capitalizeName(String name) {
        if (name == null) return null;
        return Arrays.stream(name.split("\\s+"))
            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
            .collect(Collectors.joining(" "));
    }

    private String formatPhone(String phone) {
        // Formata (99) 99999-9999
        if (phone == null) return null;
        return phone.replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    }

    private String formatDocument(String doc, DocumentType type) {
        if (doc == null) return null;
        return type == DocumentType.CPF 
            ? doc.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4")
            : doc.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    // Getters
    public Long getId() { return id; }
    // ... outros getters
}