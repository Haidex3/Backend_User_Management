package edu.eci.cvds.userManagement.controller;

import edu.eci.cvds.userManagement.model.Course;
import edu.eci.cvds.userManagement.model.Grade;
import edu.eci.cvds.userManagement.model.Responsible;
import edu.eci.cvds.userManagement.model.UserManagementException;
import edu.eci.cvds.userManagement.service.FindService;
import edu.eci.cvds.userManagement.service.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping
public class FindController {
    private final FindService findService;
    private final JwtService jwtService;

    public FindController(FindService findService, JwtService jwtService){
        this.findService = findService;
        this.jwtService = jwtService;
    }

    @GetMapping("/course/name")
    public Course findCourseByName(@RequestHeader("Authorization") String token, @RequestParam String name) {
        return findService.findCourseByName(name);
    }


    @GetMapping("/grade/name")
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

    @GetMapping("/responsible/document")
    public Responsible findResponsibleByDocument(
            @RequestParam String responsibleDocNumber,
            @RequestHeader("Authorization") String token) throws UserManagementException {
        verifyTokenDates(token);
        Responsible responsible = findService.findResponsibleByDocument(responsibleDocNumber);
        if (responsible == null) {
            throw new UserManagementException(UserManagementException.RESPONSIBLE_NOT_FOUND);
        }
        return responsible;
    }

    @GetMapping("/courses/grade")
    public List<Course> findCoursesByGrade(
            @RequestParam String gradeName,
            @RequestHeader("Authorization") String token) throws UserManagementException {
        verifyTokenDates(token);
        return findService.findCoursesByGradeName(gradeName);
    }

    private void verifyTokenDates(@RequestHeader("Authorization") String token) throws UserManagementException {
        Claims claims = jwtService.parseToken(token);

        if (!jwtService.isTokenExpired(claims)) {
            throw new UserManagementException(UserManagementException.EXPIRED_TOKEN);
        }
        String role = claims.get("role", String.class);
        if (!"administrator".equals(role)) {
            throw new UserManagementException(UserManagementException.ACCESS_DENIED);
        }
    }
}
