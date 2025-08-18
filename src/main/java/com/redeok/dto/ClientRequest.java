package com.redeok.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class ClientRequest {

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(max = 100, message = "Nome muito longo (máx. 100 caracteres)")
    private String name;

    @Pattern(regexp = "^\\+?[0-9\\s-]{10,20}$", 
             message = "Telefone inválido. Use apenas números, com DDD")
    private String phone;

    @Email(message = "E-mail inválido")
    @NotBlank
    private String email;

    @CPF(message = "CPF inválido")
    @CNPJ(message = "CNPJ inválido")
    private String document;

    // Construtor personalizado para conversão
    public ClientRequest(String name, String phone, String email, String document) {
        this.name = name.trim();
        this.phone = phone.replaceAll("[^0-9+]", "");
        this.email = email.toLowerCase().trim();
        this.document = document.replaceAll("[^0-9]", "");
    }

    // Getters (sem setters para imutabilidade)
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getDocument() { return document; }
}