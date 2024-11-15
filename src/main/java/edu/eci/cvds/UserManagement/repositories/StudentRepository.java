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
        String sql = "INSERT INTO public.students (id, documenttype, document, userName, password, name, responsibledocument, course) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";  // La consulta está corregida

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, student.getId());
            statement.setString(2, student.getDocumentType());
            statement.setLong(3, student.getDocument());
            statement.setNull(4, Types.VARCHAR);
            statement.setNull(5, Types.VARCHAR);
            statement.setString(6, student.getName());
            statement.setLong(7, student.getResponsibleDocument());
            statement.setString(8, student.getCourse());

            // Ejecutar la actualización
            statement.executeUpdate();
        }
    }
}
