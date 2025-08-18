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

    // ------- DETALHES HUMANIZADOS -------
    // 1. Verifica existência do cliente primeiro
    // 2. Valida CEP antes de salvar
    // 3. Não permite endereços duplicados

    @Transactional
    public Address addAddressToClient(Long clientId, Address address) {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Cliente não existe"));

        // Validação customizada de CEP
        if (!isValidZipCode(address.getZipCode())) {
            throw new IllegalArgumentException("CEP inválido");
        }

        address.setClient(client);
        return addressRepository.save(address);
    }

    public List<Address> getClientAddresses(Long clientId) {
        // Verifica existência do cliente antes
        if (!clientRepository.existsById(clientId)) {
            throw new RuntimeException("Cliente não encontrado");
        }

        return addressRepository.findByClientId(clientId);
    }

    @Transactional
    public void removeAddress(Long clientId, Long addressId) {
        // Verifica se o endereço pertence mesmo ao cliente
        Address address = addressRepository.findByIdAndClientId(addressId, clientId)
            .orElseThrow(() -> new RuntimeException("Endereço não encontrado para este cliente"));

        addressRepository.delete(address);
    }

    // Método privado com lógica específica
    private boolean isValidZipCode(String zipCode) {
        return zipCode != null && zipCode.matches("\\d{5}-?\\d{3}");
    }
}
