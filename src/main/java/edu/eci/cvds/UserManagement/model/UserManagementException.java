package edu.eci.cvds.UserManagement.model;
public class UserManagementException extends Exception {

    public static final String USER_NOT_FOUND = "User not found with the given ID.";
    public static final String INVALID_USER_DATA = "The provided user data is invalid.";
    public static final String DUPLICATE_USER = "A user with the given information already exists.";
    public static final String DATABASE_CONNECTION_ERROR = "Unable to connect to the database.";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access attempt detected.";
    public static final String USER_CREATION_FAILED = "Failed to create a new user.";
    public static final String USER_UPDATE_FAILED = "Failed to update the user information.";
    public static final String USER_DELETION_FAILED = "Failed to delete the user.";
    public static final String EMAIL_ALREADY_EXISTS = "A user with the given email already exists.";

    public UserManagementException (String message)	{
        super(message);
    }

}
