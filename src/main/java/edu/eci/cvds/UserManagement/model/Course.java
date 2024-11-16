package edu.eci.cvds.UserManagement.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses", schema = "public")
public class Course {

    @Id
    private String id;
    private String name;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
