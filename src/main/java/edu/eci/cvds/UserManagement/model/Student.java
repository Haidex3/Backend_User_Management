package edu.eci.cvds.UserManagement.model;
/**
 * The Student class represents a student entity, including information about
 * their academic course, year, responsible person, and the relationship with the responsible person.
 */
public class Student {

    private final Long id;
    private final String name;
    private String course;
    private int academicYear;
    private Responsible responsible;
    private final String relationWhitResponsible;

    /**
     * Constructs a new Student object with the specified details.
     *
     * @param id the unique identifier of the student (as a String, converted to Long).
     * @param name the name of the student.
     * @param course the current course of the student.
     * @param academicYear the academic year of the student.
     * @param responsible the Responsible object representing the person responsible for the student.
     * @param relationWhitResponsible the relationship between the student and the responsible person.
     */
    public Student (String id, String name, String course, int academicYear, Responsible responsible, String relationWhitResponsible){
        this.id = Long.valueOf(id);
        this.name=name;
        this.course=course;
        this.academicYear=academicYear;
        this.responsible=responsible;
        this.relationWhitResponsible=relationWhitResponsible;
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
     * Gets the academic year of the student.
     *
     * @return the academic year.
     */
    public int getAcademicYear() {
        return academicYear;
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
     * Gets the responsible person for the student.
     *
     * @return the Responsible object.
     */
    public Responsible getResponsible() {
        return responsible;
    }

    /**
     * Sets the academic year of the student.
     *
     * @param academicYear the new academic year.
     */
    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
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

    /**
     * Gets the relationship between the student and the responsible person.
     *
     * @return the relationship description.
     */
    public String getRelationWhitResponsible() {
        return relationWhitResponsible;
    }

    /**
     * Sets the responsible person for the student.
     *
     * @param responsible the new Responsible object.
     */
    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }
}
