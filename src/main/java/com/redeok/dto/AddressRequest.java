package com.redeok.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class AddressRequest {

    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 100, message = "Rua muito longa (máx. 100 caracteres)")
    private String street;

    @NotBlank(message = "Número é obrigatório")
    @Pattern(regexp = "^[0-9]{1,5}[A-Za-z]?$", 
             message = "Número inválido. Ex: 123 ou 123A")
    private String number;

    @Size(max = 50, message = "Complemento muito longo")
    private String complement;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 50, message = "Bairro muito longo")
    private String neighborhood;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 50, message = "Cidade muito longa")
    private String city;

    @NotBlank(message = "Estado é obrigatório")
    @Length(min = 2, max = 2, message = "Use a sigla com 2 letras")
    private String state;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "^[0-9]{5}-?[0-9]{3}$", 
             message = "CEP inválido. Formato: 12345-678 ou 12345678")
    private String zipCode;

    // Construtor com sanitização
    public AddressRequest(String street, String number, String complement, 
                        String neighborhood, String city, String state, String zipCode) {
        this.street = street.trim();
        this.number = number.trim().toUpperCase();
        this.complement = complement != null ? complement.trim() : null;
        this.neighborhood = neighborhood.trim();
        this.city = city.trim();
        this.state = state.trim().toUpperCase();
        this.zipCode = zipCode.replace("-", "");
    }

    // Getters (sem setters para imutabilidade)
    public String getStreet() { return street; }
    public String getNumber() { return number; }
    public String getComplement() { return complement; }
    public String getNeighborhood() { return neighborhood; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }

    // Método útil para conversão
    public String getFormattedAddress() {
        return String.format("%s, %s%s - %s, %s/%s",
            street,
            number,
            complement != null ? " " + complement : "",
            neighborhood,
            city,
            state);
    }
}