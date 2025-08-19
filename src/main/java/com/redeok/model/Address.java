package com.redeok.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 150, message = "Rua muito longa (máx. 150 caracteres)")
    private String street;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número muito longo (máx. 10 caracteres)")
    private String number;

    @Size(max = 50, message = "Complemento muito longo (máx. 50 caracteres)")
    private String complement;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro muito longo (máx. 100 caracteres)")
    private String neighborhood;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade muito longa (máx. 100 caracteres)")
    private String city;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres (ex: SP, RJ)")
    private String state;

    @NotBlank(message = "CEP é obrigatório")
    @Size(min = 8, max = 9, message = "CEP deve ter 8 ou 9 caracteres")
    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "primary_address")
    private boolean primary = false;

    // Relacionamento N:1 com Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Construtores
    public Address() {}

    public Address(String street, String number, String complement,
                   String neighborhood, String city, String state, String zipCode) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    // Getters & Setters
    public Long getId() { return id; }
    
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getComplement() { return complement; }
    public void setComplement(String complement) { this.complement = complement; }

    public String getNeighborhood() { return neighborhood; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public boolean isPrimary() { return primary; }
    public void setPrimary(boolean primary) { this.primary = primary; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
