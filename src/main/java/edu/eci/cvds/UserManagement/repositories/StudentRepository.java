package edu.eci.cvds.UserManagement.repositories;
import edu.eci.cvds.UserManagement.model.Student;
import edu.eci.cvds.UserManagement.repositories.connection.DBConnection;
import org.springframework.stereotype.Repository;


import java.sql.*;

import java.sql.SQLException;

/**
 * StudentRepository is a data access class that manages database interactions for
 * the Student entity, allowing saving of Student records to the database.
 */
@Repository
public class StudentRepository {

    /**
     * Saves a Student entity to the database.
     *
     * @param student The Student entity to be saved.
     * @throws SQLException if an SQL exception occurs during the save operation.
     */
    public void saveStudent(Student student) throws SQLException {
        String sql = "INSERT INTO public.students (id, name, course, academic_year,relation_with_responsible, id_responsible) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getAcademicYear());
            statement.setString(5, student.getResponsible() != null ? student.getResponsible().toString() : null);
            statement.setString(6, student.getRelationWhitResponsible());

            statement.executeUpdate();
        }
    }
}
