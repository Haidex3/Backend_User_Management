package edu.eci.cvds.UserManagement.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses", schema = "public")
public class Course {

    @Id
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Grade grade;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();


    public Course(String name, Grade grade) {
        this.name = name;
        this.grade=grade;
        grade.addCourse(this);
    }

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student){
        students.add(student);
    }
}
