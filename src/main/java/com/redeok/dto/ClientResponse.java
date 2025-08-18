package com.redeok.dto;

import com.redeok.model.DocumentType;

public class ClientResponse {

    private Long id;
    private String name;
    private String email;
    private String document;
    private DocumentType documentType;

    public ClientResponse(Long id, String name, String email, String document, DocumentType documentType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = formatDocument(document, documentType);
        this.documentType = documentType;
    }

    public Long getId() {
        return id;
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

    private String formatDocument(String doc, DocumentType type) {
        if (doc == null) return null;

        String digits = doc.replaceAll("\\D", ""); // mantém só números

        if (type == DocumentType.CPF && digits.length() == 11) {
            return digits.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                    "$1.$2.$3-$4");
        } else if (type == DocumentType.CNPJ && digits.length() == 14) {
            return digits.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})",
                    "$1.$2.$3/$4-$5");
        }

        // Se não bater com nenhum formato esperado, retorna como está
        return doc;
    }
}
