package edu.eci.cvds.UserManagement.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Student class represents a student entity, including information about
 * their academic course, year, responsible person, and the relationship with the responsible person.
 */
public class Student {

    private final Long id;
    private final String name;
    private final String userName;
    private String password;
    private final Long document;
    private final String documentType;
    private String course;
    private String grade;
    private final Long responsibleDocument;


    public Student (Long id, String name,Long document, String documentType, String course, String grade, Long responsibleDocument){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(String.valueOf(id));
        this.id = id;
        this.name=name;
        this.userName = name;
        this.password = encodedPassword;
        this.document = document;
        this.documentType = documentType;
        this.course = course;
        this.grade = grade;
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

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
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
