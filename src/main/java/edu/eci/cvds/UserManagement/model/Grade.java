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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "name")
    private List<Course> courses = new ArrayList<>();

    public Grade(String name, String year){
        this.name=name;
        this.courses = new ArrayList<>();
        //this.year=year;
    }

    public Grade() {
    }

    public void addCourse(Course course){
        courses.add(course);
    }
}
