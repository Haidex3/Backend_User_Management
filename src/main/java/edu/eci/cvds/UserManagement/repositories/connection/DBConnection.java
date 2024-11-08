package edu.eci.cvds.UserManagement.repositories.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://libraryusermanagement.postgres.database.azure.com:5432/usermanagement?sslmode=require";
    private static final String USER = "libraryDirector";
    private static final String PASSWORD = "userManagement2024";

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * <p>This method uses JDBC to connect to a PostgreSQL database with SSL mode required.</p>
     *
     * @return a Connection object representing the established database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}