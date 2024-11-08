package edu.eci.cvds.UserManagement.model;
public class Student {

    private final Long id;
    private final String name;
    private String course;
    private int academicYear;
    private Responsible responsible;
    private final String relationWhitResponsible;

    public Student (String id, String name, String course, int academicYear, Responsible responsible, String relationWhitResponsible){
        this.id = Long.valueOf(id);
        this.name=name;
        this.course=course;
        this.academicYear=academicYear;
        this.responsible=responsible;
        this.relationWhitResponsible=relationWhitResponsible;
    }

    public Long getId() {
        return id;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public String getCourse() {
        return course;
    }

    public Responsible getResponsible() {
        return responsible;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getRelationWhitResponsible() {
        return relationWhitResponsible;
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }
}