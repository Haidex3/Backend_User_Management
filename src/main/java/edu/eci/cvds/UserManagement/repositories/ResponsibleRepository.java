package edu.eci.cvds.UserManagement.repositories;

import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.repositories.connection.DBConnection;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.sql.SQLException;

@Repository
public class ResponsibleRepository {

    public void saveResponsible(Responsible responsible) throws SQLException {
        String sql = "INSERT INTO responsibles (id, name, email, phone_number, address, document, type_document) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, responsible.getDocument());
            statement.setString(1, responsible.getName());
            statement.setString(2, responsible.getEmail());
            statement.setString(3, responsible.getPhoneNumber());
            statement.setString(4, responsible.getAddress());
            //statement.setString(5, responsible.getDocument());
            statement.setString(6, responsible.getTypeDocument());

            statement.executeUpdate();
        }
    }

    public Responsible findResponsibleByDocument(String responsibleDocType, String responsibleDocNumber) throws SQLException {
        String sql = "SELECT * FROM responsibles WHERE type_document = ? AND document = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, responsibleDocType);
            statement.setString(2, responsibleDocNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Responsible(resultSet.getLong("document"), resultSet.getString("type_document"),resultSet.getString("name"), resultSet.getString("phone_number"), resultSet.getString("email"),resultSet.getString("address"));
                } else {
                    return null;
                }
            }
        }
    }

}
