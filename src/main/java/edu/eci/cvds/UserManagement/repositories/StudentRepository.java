package edu.eci.cvds.UserManagement.repositories;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;




/**
 * StudentRepository is a data access class that manages database interactions for
 * the Student entity, allowing saving of Student records to the database.
 */
@Repository
public interface StudentRepository extends JpaRepository<Responsible, Long> {

    Optional<Responsible> save(Student student);

}
