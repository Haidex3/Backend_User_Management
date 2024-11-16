package edu.eci.cvds.UserManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Student class represents a student entity, including information about
 * their academic course, year, responsible person, and the relationship with the responsible person.
 */

@Entity
@Table(name = "students", schema = "public")
public class Student {

    @Id

    private Long id;

    private String name;
    private String userName;
    private String password;
    private Long document;
    private String documentType;
    private String course;
    private Long responsibleDocument;

    protected Student() {
    }

    public Student (Long id, String name,Long document, String documentType, String course, Long responsibleDocument){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(String.valueOf(id));
        this.id = id;
        this.name=name;
        this.userName = name;
        this.password = encodedPassword;
        this.document = document;
        this.documentType = documentType;
        this.course = course;
        this.responsibleDocument = responsibleDocument;
    }

    public String getUserName() {
        return userName;
    }

    public Long getResponsibleDocument() {
        return responsibleDocument;
    }

    public Long getDocument() {
        return document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the unique identifier of the student.
     *
     * @return the student ID.
     */
    public Long getId() {
        return id;
    }


    /**
     * Gets the course of the student.
     *
     * @return the course name.
     */
    public String getCourse() {
        return course;
    }


    /**
     * Sets the course of the student.
     *
     * @param course the new course name.
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Gets the name of the student.
     *
     * @return the student's name.
     */
    public String getName() {
        return name;
    }

}
