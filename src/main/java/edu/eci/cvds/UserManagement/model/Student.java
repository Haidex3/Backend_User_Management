package edu.eci.cvds.UserManagement.model;

import jakarta.persistence.DiscriminatorValue;
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
public class Student extends User{
    private String name;
    private String document;
    private String documentType;
    private String course;
    private String responsibleDocument;


    public Student (String id, String name,String document, String documentType, String course, String responsibleDocument){
        super(id, name, null);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(String.valueOf(id));
        setUserName(name);
        setPassword(encodedPassword);
        this.name=name;
        this.document = document;
        this.documentType = documentType;
        this.course = course;
        this.responsibleDocument = responsibleDocument;
    }

    public Student() {
        super();
    }

    public String getUserName() {
        return getUserName();
    }

    public String getResponsibleDocument() {
        return responsibleDocument;
    }

    public String getDocument() {
        return document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getPassword() {
        return getPassword();
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
