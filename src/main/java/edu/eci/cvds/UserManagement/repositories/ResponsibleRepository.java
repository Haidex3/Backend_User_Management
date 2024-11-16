package edu.eci.cvds.UserManagement.repositories;

import edu.eci.cvds.UserManagement.model.Responsible;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * ResponsibleRepository is a data access class that manages database interactions for
 * the Responsible entity. It allows saving a new Responsible record and retrieving a
 * Responsible by document type and number.
 */
@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, String> {


    /**
     * Find a Responsible by typeDocument and document.
     *
     * @param document     the document number.
     * @return an Optional containing the Responsible if found, otherwise empty.
     */
    Optional<Responsible> findByDocument(String document);

    Responsible save(Responsible responsible);

}


