package edu.eci.cvds.UserManagement.repositories;
import edu.eci.cvds.UserManagement.model.Student;
import edu.eci.cvds.UserManagement.repositories.connection.DBConnection;
import org.springframework.stereotype.Repository;


import java.sql.*;

import java.sql.SQLException;

@Repository
public class StudentRepository {

    public void saveStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, name, course, academic_year, responsible, relation_with_responsible) VALUES (?, ?, ?, ?, ?, ?)";

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
