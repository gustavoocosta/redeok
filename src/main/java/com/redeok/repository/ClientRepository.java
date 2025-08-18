package com.redeok.repository;

import com.redeok.model.Client;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    @SqlUpdate("INSERT INTO client (name, phone, email, document, document_type) " +
               "VALUES (:name, :phone, :email, :document, :documentType)")
    @GetGeneratedKeys
    long create(@BindBean Client client);

    @SqlQuery("SELECT * FROM client WHERE id = :id")
    @RegisterBeanMapper(Client.class)
    Optional<Client> findById(@Bind("id") long id);

    @SqlQuery("SELECT * FROM client WHERE document = :document")
    @RegisterBeanMapper(Client.class)
    Optional<Client> findByDocument(@Bind("document") String document);

    @SqlQuery("SELECT * FROM client ORDER BY name LIMIT :limit OFFSET :offset")
    @RegisterBeanMapper(Client.class)
    List<Client> findAll(@Bind("offset") int offset, @Bind("limit") int limit);

    @SqlQuery("SELECT * FROM client WHERE name LIKE :name ORDER BY name LIMIT :limit OFFSET :offset")
    @RegisterBeanMapper(Client.class)
    List<Client> findByName(@Bind("name") String name, 
                          @Bind("offset") int offset, 
                          @Bind("limit") int limit);

    @SqlUpdate("UPDATE client SET name = :name, phone = :phone, email = :email " +
               "WHERE id = :id")
    void update(@BindBean Client client);

    @SqlUpdate("DELETE FROM client WHERE id = :id")
    void delete(@Bind("id") long id);
}