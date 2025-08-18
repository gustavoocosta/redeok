package com.redeok.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.redeok.model.Address;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {

    private Long id;
    private String formattedStreet;
    private String neighborhood;
    private String cityState;
    private String postalCode;
    private boolean primary;

    public AddressResponse(Address address) {
        this.id = address.getId();
        this.formattedStreet = formatStreet(address);
        this.neighborhood = address.getNeighborhood();
        this.cityState = formatCityState(address);
        this.postalCode = formatPostalCode(address.getZipCode());
        this.primary = address.isPrimary();
    }

    private String formatStreet(Address address) {
        return String.format("%s, %s%s",
            address.getStreet(),
            address.getNumber(),
            address.getComplement() != null ? " " + address.getComplement() : "");
    }

    private String formatCityState(Address address) {
        return String.format("%s/%s",
            capitalize(address.getCity()),
            address.getState().toUpperCase());
    }

    private String formatPostalCode(String zipCode) {
        if (zipCode == null) return null;
        return zipCode.replaceFirst("(\\d{5})(\\d{3})", "$1-$2");
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public Long getId() { return id; }
    public String getFormattedStreet() { return formattedStreet; }
    public String getNeighborhood() { return neighborhood; }
    public String getCityState() { return cityState; }
    public String getPostalCode() { return postalCode; }
    public boolean isPrimary() { return primary; }
}