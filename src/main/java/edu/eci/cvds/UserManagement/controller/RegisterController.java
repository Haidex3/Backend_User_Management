package edu.eci.cvds.UserManagement.controller;

import edu.eci.cvds.UserManagement.service.FindService;
import edu.eci.cvds.UserManagement.service.RegisterService;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This class provides endpoints to register students and responsible persons in the system.
 * It exposes two POST endpoints:
 * - `/registerStudent` for registering a new student.
 * - `/registerResponsible` for registering a new responsible person.
 *
 * Each method processes a registration request, interacts with the RegisterService to persist the data,
 * and returns an appropriate response with success or error messages.
 */
@RestController
@RequestMapping
public class RegisterController {
    private final RegisterService registerService;
    private final FindService findService;

    /**
     * Constructor to initialize the RegisterController with the required RegisterService dependency.
     *
     * @param registerService the service responsible for handling the registration of students and responsible persons.
     */
    public RegisterController(RegisterService registerService, FindService newFindService) {
        this.registerService = registerService;
        this.findService = newFindService;
    }

    /**
     * Endpoint to handle the registration of a new student.
     * This method accepts a `Student` object in the request body, attempts to register the student using the
     * RegisterService, and returns a response with a success or failure message.
     *
     * @param newStudent the student to be registered.
     * @return a ResponseEntity containing the result of the registration attempt.
     */
    @PostMapping("/registerStudent")
    public ResponseEntity<Map<String, Object>> registerStudent(@RequestBody Student newStudent) {
        Map<String, Object> response = new HashMap<>();
        Optional<Student> studentOptional = registerService.registerStudent(newStudent);
        if (studentOptional.isPresent()) {
            response.put("message", "Registration of Student successful!");
            response.put("Student", studentOptional.get());
            response.put("roleId", studentOptional.get().getDocument());
            return ResponseEntity.ok(response);
        }else {
            response.put("message", "Registration failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Endpoint to handle the registration of a new responsible person.
     * This method accepts a `Responsible` object in the request body, attempts to register the responsible
     * person using the RegisterService, and returns a response with a success or failure message.
     *
     * @param newResponsible the responsible person to be registered.
     * @return a ResponseEntity containing the result of the registration attempt.
     */
    @PostMapping("/registerResponsible")
    public ResponseEntity<Map<String, Object>> registerResponsible(@RequestBody Responsible newResponsible) {
        Map<String, Object> response = new HashMap<>();
        Optional<Responsible> responsibleOptional = registerService.registerResponsible(newResponsible);
        if (responsibleOptional.isPresent()) {
            response.put("message", "Registration of Responsible successful!");
            response.put("Student", responsibleOptional.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Registration failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/findResponsibleByDocument")
    public Responsible findResponsibleByDocument(
            @RequestParam Long responsibleDocNumber) throws SQLException {
        return findService.findResponsibleByDocument(responsibleDocNumber);
    }
}
