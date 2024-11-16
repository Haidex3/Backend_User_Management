package edu.eci.cvds.UserManagement.service;

import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import edu.eci.cvds.UserManagement.repositories.ResponsibleRepository;
import edu.eci.cvds.UserManagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    private final ResponsibleRepository responsibleRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public RegisterService(ResponsibleRepository responsibleRepository, StudentRepository studentRepository) {
        this.responsibleRepository = responsibleRepository;
        this.studentRepository = studentRepository;
    }

    public Optional<Student> registerStudent(Student student) {
        return Optional.of(studentRepository.save(student));
    }


    public Optional<Responsible> registerResponsible(Responsible responsible) {
        return Optional.ofNullable(responsibleRepository.save(responsible));
    }

}
