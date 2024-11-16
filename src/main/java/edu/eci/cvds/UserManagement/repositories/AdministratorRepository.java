package edu.eci.cvds.UserManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.eci.cvds.UserManagement.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {

}

