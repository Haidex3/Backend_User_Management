package edu.eci.cvds.UserManagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grades", schema = "public")
public class Grade {

    @Id
    public String name;
    @Column
    private String description;


    public Grade(String name, String description){
        this.name=name;
        this.description=description;

    }

    public Grade() {
    }

}
