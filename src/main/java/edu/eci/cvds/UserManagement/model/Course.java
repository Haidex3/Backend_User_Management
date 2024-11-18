package edu.eci.cvds.UserManagement.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses", schema = "public")
public class Course {

    @Id
    private String name;

    @Column(name = "grade_name")
    private String gradeName;


    public Course(String name, String gradeName) {
        this.name = name;
        this.gradeName=gradeName;
    }

    public Course() {
    }

    public String getGradeName() {
        return gradeName;
    }

    public String getName() {
        return name;
    }

}
