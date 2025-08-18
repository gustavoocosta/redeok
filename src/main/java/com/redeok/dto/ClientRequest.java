package com.redeok.dto;

import com.redeok.model.DocumentType;
import jakarta.validation.constraints.*;

public class ClientRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome muito longo (máx. 100 caracteres)")
    private String name;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Documento é obrigatório")
    private String document;

    @NotNull(message = "Tipo de documento é obrigatório")
    private DocumentType documentType;

    public ClientRequest(String name, String email, String document, DocumentType documentType) {
        this.name = name.trim();
        this.email = email.trim().toLowerCase();
        this.documentType = documentType;
        this.document = sanitizeDocument(document, documentType);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    private String sanitizeDocument(String doc, DocumentType type) {
        if (doc == null) return null;

        String digits = doc.replaceAll("\\D", ""); // mantém apenas números

        if (type == DocumentType.CPF) {
            if (digits.length() != 11) {
                throw new IllegalArgumentException("CPF deve ter 11 dígitos");
            }
        } else if (type == DocumentType.CNPJ) {
            if (digits.length() != 14) {
                throw new IllegalArgumentException("CNPJ deve ter 14 dígitos");
            }
        }
        return digits;
    }
}
