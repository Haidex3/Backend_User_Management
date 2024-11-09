package edu.eci.cvds.UserManagement.repositories;

import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.repositories.connection.DBConnection;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.sql.SQLException;


/**
 * ResponsibleRepository is a data access class that manages database interactions for
 * the Responsible entity. It allows saving a new Responsible record and retrieving a
 * Responsible by document type and number.
 */
@Repository
public class ResponsibleRepository {

    /**
     * Saves a Responsible entity to the database.
     *
     * @param responsible The Responsible entity to be saved.
     * @throws SQLException if an SQL exception occurs during the save operation.
     */
    public void saveResponsible(Responsible responsible) throws SQLException {
        String sql = "INSERT INTO public.responsibles (document, identification_type,name, phone_number, email, address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, responsible.getDocument());
            statement.setString(3, responsible.getName());
            statement.setString(5, responsible.getEmail());
            statement.setString(4, responsible.getPhoneNumber());
            statement.setString(6, responsible.getAddress());
            //statement.setString(5, responsible.getDocument());
            statement.setString(2, responsible.getTypeDocument());

            statement.executeUpdate();
        }
    }

    /**
     * Finds and retrieves a Responsible entity by its document type and number.
     *
     * @param responsibleDocType  The document type of the Responsible.
     * @param responsibleDocNumber The document number of the Responsible.
     * @return The Responsible entity if found, otherwise null.
     * @throws SQLException if an SQL exception occurs during the retrieval.
     */
    public Responsible findResponsibleByDocument(String responsibleDocType, Long responsibleDocNumber) throws SQLException {
        String sql = "SELECT * FROM public.responsibles WHERE document = ? AND identification_type = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            String prueba= "CC";

            statement.setString(2, prueba);
            statement.setLong(1, responsibleDocNumber);

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

