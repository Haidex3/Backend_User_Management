package edu.eci.cvds.UserManagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grades", schema = "public")
public class Grade {

    @Id
    public String name;
    /*
    @Column(name = "year")
    private String year;*/


    public Grade(String name, String year){
        this.name=name;

    }

    public Grade() {
    }

}
