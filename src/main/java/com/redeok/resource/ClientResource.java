package com.redeok.resource;

import com.redeok.model.Client;
import com.redeok.repository.ClientRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientRepository clientRepository;

    @GET
    public List<Client> listAll() {
        return clientRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Client findById(@PathParam("id") Long id) {
        return clientRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    @POST
    @Transactional
    public Response create(@Valid Client client) {
        clientRepository.persist(client);
        return Response.status(Response.Status.CREATED).entity(client).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Client update(@PathParam("id") Long id, @Valid Client client) {
        Client entity = clientRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        entity.setName(client.getName());
        entity.setEmail(client.getEmail());

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        boolean deleted = clientRepository.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Cliente não encontrado");
        }
    }
}
