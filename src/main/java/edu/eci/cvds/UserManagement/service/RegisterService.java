package edu.eci.cvds.UserManagement.service;

import edu.eci.cvds.UserManagement.model.Course;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import edu.eci.cvds.UserManagement.repositories.AdministratorRepository;
import edu.eci.cvds.UserManagement.repositories.CourseRepository;
import edu.eci.cvds.UserManagement.repositories.ResponsibleRepository;
import edu.eci.cvds.UserManagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    private final ResponsibleRepository responsibleRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final AdministratorRepository administratorRepository;

    @Autowired
    public RegisterService(ResponsibleRepository responsibleRepository, StudentRepository studentRepository, CourseRepository courseRepository, AdministratorRepository administratorRepository) {
        this.responsibleRepository = responsibleRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.administratorRepository = administratorRepository;
    }

    public Optional<Student> registerStudent(Student student) {
        return Optional.of(studentRepository.save(student));
    }

    public Optional<Responsible> registerResponsible(Responsible responsible) {
        return Optional.ofNullable(responsibleRepository.save(responsible));
    }

    public Course createCourse(Course course){
        return (Course) courseRepository.save(course);
    }

}
