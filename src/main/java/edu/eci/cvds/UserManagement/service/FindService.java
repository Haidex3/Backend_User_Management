package edu.eci.cvds.UserManagement.service;

import edu.eci.cvds.UserManagement.model.Course;
import edu.eci.cvds.UserManagement.model.Grade;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.repositories.CourseRepository;
import edu.eci.cvds.UserManagement.repositories.GradeRepository;
import edu.eci.cvds.UserManagement.repositories.ResponsibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindService {
    private final ResponsibleRepository responsibleRepository;
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public FindService(ResponsibleRepository responsibleRepository, GradeRepository gradeRepository, CourseRepository courseRepository) {
        this.responsibleRepository = responsibleRepository;

        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    public Responsible findResponsibleByDocument(String document) {
        Optional<Responsible> optionalResponsible = responsibleRepository.findByDocument(document);
        return optionalResponsible.orElse(null);
    }

    public Grade findGradeByName(String name){
        return gradeRepository.findByName(name);
    }

    public Course findCourseByName(String name){
        return courseRepository.findByName(name);
    }
}
