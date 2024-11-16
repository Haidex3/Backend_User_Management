package edu.eci.cvds.UserManagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    protected User() {
    }

    public User(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

