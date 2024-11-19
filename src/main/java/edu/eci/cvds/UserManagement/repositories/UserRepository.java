package edu.eci.cvds.UserManagement.repositories;


import edu.eci.cvds.UserManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
