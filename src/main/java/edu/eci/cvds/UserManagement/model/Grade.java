package edu.eci.cvds.UserManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grades", schema = "public")
public class Grade {

    @Id
    public String name;

    public Grade(String name){
        this.name=name;
    }

    public Grade() {

    }
}
