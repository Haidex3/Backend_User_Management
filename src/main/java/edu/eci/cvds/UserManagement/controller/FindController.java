package edu.eci.cvds.UserManagement.controller;

import edu.eci.cvds.UserManagement.model.Course;
import edu.eci.cvds.UserManagement.model.Grade;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.service.FindService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping
public class FindController {
    private final FindService findService;

    public FindController(FindService findService){
        this.findService = findService;
    }

    @GetMapping("/findCourseByName")
    public Course findCourseByName(String name){
        return findService.findCourseByName(name);
    }

    @GetMapping("/findGradeByName")
    public Grade findGradeByName(String name){
        return findService.findGradeByName(name);
    }

    @GetMapping("/findResponsibleByDocument")
    public Responsible findResponsibleByDocument(
            @RequestParam String responsibleDocNumber) throws SQLException {
        return findService.findResponsibleByDocument(responsibleDocNumber);
    }

}
