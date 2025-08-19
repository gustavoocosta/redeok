package com.redeok.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome muito longo (máx. 100 caracteres)")
    private String name;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Documento é obrigatório")
    @Column(unique = true)
    private String document;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Relacionamento 1:N com Address
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    // Construtores
    public Client() {
        this.createdAt = LocalDateTime.now();
    }

    public Client(String name, String email, String document, DocumentType documentType) {
        this();
        this.name = name;
        this.email = email;
        this.document = document;
        this.documentType = documentType;
    }

    // Getters e Setters
    public Long getId() { return id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDocument() { return document; }
    public void setDocument(String document) { this.document = document; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    // Métodos utilitários
    public void addAddress(Address address) {
        addresses.add(address);
        address.setClient(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setClient(null);
    }
}