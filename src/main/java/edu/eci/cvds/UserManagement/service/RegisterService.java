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
        try{
            studentRepository.saveStudent(student);
            return Optional.of(student);
        }
        catch(Exception e){
            return Optional.empty();
        }
    }

    public Optional<Responsible> registerResponsible(Responsible responsible) {
        try{
            responsibleRepository.saveResponsible(responsible);
            return Optional.of(responsible);
        }
        catch(Exception e){
            return Optional.empty();
        }
    }


    public Optional<edu.eci.cvds.UserManagement.model.Responsible> findResponsibleByDocument(String responsibleDocType, Long responsibleDocNumber) {
        try{
            return Optional.ofNullable(responsibleRepository.findResponsibleByDocument(responsibleDocType, responsibleDocNumber));
        }
        catch(Exception e){
            return Optional.empty();
        }
    }
}
