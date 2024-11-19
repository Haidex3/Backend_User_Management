package edu.eci.cvds.UserManagement.repositories;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;




/**
 * StudentRepository is a data access class that manages database interactions for
 * the Student entity, allowing saving of Student records to the database.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {


    /**
     * Retrieves a paginated list of students.
     *
     * @param pageable The Pageable object containing pagination information.
     * @return A Page of students.
     */
    Page<Student> findAll(Pageable pageable);

    /**
     * Updates the course of a student by their ID.
     *
     * @param id         The ID of the student.
     * @param courseName The new course name.
     */
    @Modifying
    @Query("UPDATE Student s SET s.courseName = :courseName WHERE s.id = :id")
    void updateStudentCourse(@Param("id") String id, @Param("courseName") String courseName);
}
