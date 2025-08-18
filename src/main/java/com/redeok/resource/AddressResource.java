package com.redeok.resource;

import com.redeok.model.Address;
import com.redeok.model.Client;
import com.redeok.repository.AddressRepository;
import com.redeok.repository.ClientRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clients/{clientId}/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    AddressRepository addressRepository;

    @Inject
    ClientRepository clientRepository;

    @GET
    public List<Address> listAll(@PathParam("clientId") Long clientId) {
        return addressRepository.findByClientId(clientId);
    }

    @GET
    @Path("/{id}")
    public Address findById(@PathParam("clientId") Long clientId, @PathParam("id") Long id) {
        return addressRepository.findByIdAndClientId(id, clientId)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
    }

    @POST
    @Transactional
    public Response create(@PathParam("clientId") Long clientId, @Valid Address address) {
        Client client = clientRepository.findByIdOptional(clientId)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        address.setClient(client);
        addressRepository.persist(address);

        return Response.status(Response.Status.CREATED).entity(address).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Address update(@PathParam("clientId") Long clientId, @PathParam("id") Long id, @Valid Address address) {
        Address entity = addressRepository.findByIdAndClientId(id, clientId)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));

        entity.setStreet(address.getStreet());
        entity.setNumber(address.getNumber());
        entity.setComplement(address.getComplement());
        entity.setNeighborhood(address.getNeighborhood());
        entity.setCity(address.getCity());
        entity.setState(address.getState());
        entity.setZipCode(address.getZipCode());

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("clientId") Long clientId, @PathParam("id") Long id) {
        boolean deleted = addressRepository.deleteByIdAndClientId(id, clientId);
        if (!deleted) {
            throw new NotFoundException("Endereço não encontrado");
        }
    }
}
