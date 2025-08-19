package com.redeok.service;

import com.redeok.model.Address;
import com.redeok.model.Client;
import com.redeok.repository.AddressRepository;
import com.redeok.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AddressService {

    @Inject
    AddressRepository addressRepository;

    @Inject
    ClientRepository clientRepository;

    @Transactional
    public Address addAddressToClient(Long clientId, Address address) {
        Client client = clientRepository.findByIdOptional(clientId)
            .orElseThrow(() -> new RuntimeException("Cliente não existe"));

        if (!isValidZipCode(address.getZipCode())) {
            throw new IllegalArgumentException("CEP inválido");
        }

        address.setClient(client);
        addressRepository.persist(address);
        return address;
    }

    public List<Address> getClientAddresses(Long clientId) {
        if (clientRepository.findByIdOptional(clientId).isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }

        return addressRepository.findByClientId(clientId);
    }

    @Transactional
    public void removeAddress(Long clientId, Long addressId) {
        Address address = addressRepository.findByIdAndClientId(addressId, clientId)
            .orElseThrow(() -> new RuntimeException("Endereço não encontrado para este cliente"));

        addressRepository.delete(address);
    }

    private boolean isValidZipCode(String zipCode) {
        return zipCode != null && zipCode.matches("\\d{5}-?\\d{3}");
    }
}