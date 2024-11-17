package edu.eci.cvds.UserManagement.controller;

import edu.eci.cvds.UserManagement.model.Course;
import edu.eci.cvds.UserManagement.model.Grade;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.service.FindService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> findGradeByName(@RequestParam("name") String name) {
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre del grado es requerido.");
        }
        Grade grade = findService.findGradeByName(name);
        if (grade == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grado no encontrado :c.");
        }

        return ResponseEntity.ok(grade);
    }


    @GetMapping("/findResponsibleByDocument")
    public Responsible findResponsibleByDocument(
            @RequestParam String responsibleDocNumber) throws SQLException {
        return findService.findResponsibleByDocument(responsibleDocNumber);
    }

}
