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
        String sql = "INSERT INTO public.students (code, name, userName, password, document, document_type, course, grade, responsible_document) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getUserName());
            statement.setString(4, student.getPassword());
            statement.setLong(5, student.getDocument());
            statement.setString(6, student.getDocumentType());
            statement.setInt(7, student.getCourse());
            statement.setString(8, student.getGrade());
            statement.setString(9, student.getResponsibleDocument());
            statement.executeUpdate();
        }
    }
}
