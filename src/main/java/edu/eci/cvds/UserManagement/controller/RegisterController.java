package edu.eci.cvds.UserManagement.controller;

import edu.eci.cvds.UserManagement.service.RegisterService;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class RegisterController {
    private final RegisterService registerService;
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<Map<String, Object>> registerStudent(@RequestBody Student newStudent) {
        Map<String, Object> response = new HashMap<>();
        Optional<Student> studentOptional = registerService.registerStudent(newStudent);
        if (studentOptional.isPresent()) {
            response.put("message", "Registration of Student successful!");
            response.put("Student", studentOptional.get());
            response.put("roleId", studentOptional.get().getId());
            return ResponseEntity.ok(response);
        }else {
            response.put("message", "Registration failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

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


}
