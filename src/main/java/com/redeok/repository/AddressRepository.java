package com.redeok.repository;

import com.redeok.model.Address;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.*;
import org.jdbi.v3.sqlobject.statement.*;
import java.util.*;

public interface AddressRepository {

    // Cria endereço vinculado a um cliente
    @SqlUpdate("INSERT INTO address (client_id, street, number, complement, " +
               "neighborhood, city, state, zip_code) " +
               "VALUES (:clientId, :street, :number, :complement, " +
               ":neighborhood, :city, :state, :zipCode)")
    @GetGeneratedKeys
    long create(@BindBean Address address, @Bind("clientId") long clientId);

    // Lista todos endereços de um cliente
    @SqlQuery("SELECT * FROM address WHERE client_id = :clientId")
    @RegisterBeanMapper(Address.class)
    List<Address> findByClientId(@Bind("clientId") long clientId);

    // Busca específica (garante que o endereço pertence ao cliente)
    @SqlQuery("SELECT * FROM address " +
              "WHERE id = :id AND client_id = :clientId")
    @RegisterBeanMapper(Address.class)
    Optional<Address> findByIdAndClientId(
        @Bind("id") long id,
        @Bind("clientId") long clientId);

    // Delete seguro (só deleta se pertencer ao cliente)
    @SqlUpdate("DELETE FROM address " +
               "WHERE id = :id AND client_id = :clientId")
    boolean delete(
        @Bind("id") long id,
        @Bind("clientId") long clientId);
}