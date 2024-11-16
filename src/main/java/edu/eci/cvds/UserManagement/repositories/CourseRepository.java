package edu.eci.cvds.UserManagement.repositories;

import edu.eci.cvds.UserManagement.model.Course;
import edu.eci.cvds.UserManagement.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository<Curse> extends JpaRepository<Course, String> {
    Course findByName(String name);
}
