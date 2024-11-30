package edu.eci.cvds.userManagement.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Student class represents a student entity, including information about
 * their academic course, year, responsible person, and the relationship with the responsible person.
 */

@Entity
@Table(name = "students", schema = "public")
public class Student extends User{
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String name;
    private String document;
    @Column(name = "document_type")
    private String documentType;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "responsible_document")
    private String responsibleDocument;

    @Column(name = "active")
    private boolean active;

    /**
     * Constructs a new Student with the specified attributes. Automatically sets the role to "student"
     * and encodes the student's ID as their password using BCrypt.
     *
     * @param id                  The unique identifier for the student.
     * @param name                The name of the student.
     * @param document            The document number of the student.
     * @param documentType        The type of document (e.g., ID, passport).
     * @param courseName          The name of the course the student is enrolled in.
     * @param responsibleDocument The document number of the responsible person for the student.
     */
    public Student (String id, String name,String document, String documentType, String courseName, String responsibleDocument){
        this.id=id;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(String.valueOf(id));
        setUserName(name);
        setPassword(encodedPassword);
        this.name=name;
        this.document = document;
        this.documentType = documentType;
        this.courseName = courseName;
        this.responsibleDocument = responsibleDocument;
        this.active = true;
        setRole("student");
    }

    /**
     * Default constructor required by JPA. Calls the superclass constructor.
     */
    public Student() {
        super();
    }

    /**
     * Retrieves the username of the student.
     *
     * @return The username of the student.
     */
    @Override
    public String getUserName() {

        return super.getUserName();
    }

    /**
     * Retrieves the document number of the responsible person for the student.
     *
     * @return The responsible person's document number.
     */
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
        return super.getPassword();
    }

    /**
     * Sets the course of the student.
     *
     * @param courseName the new course name.
     */
    public void setCourse(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Gets the name of the student.
     *
     * @return the student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get ID.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the name of course.
     *
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Updates state of student.
     *
     * @param status The new status.
     */
    public void setActive(boolean status) {
        this.active=status;
    }
}
