package edu.eci.cvds.UserManagement.repositories;

import edu.eci.cvds.UserManagement.model.Grade;
import edu.eci.cvds.UserManagement.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, String> {
    Grade findByName(String name);
}
