package edu.eci.cvds.userManagement.controller;

import edu.eci.cvds.userManagement.model.Course;
import edu.eci.cvds.userManagement.service.RegisterService;
import edu.eci.cvds.userManagement.model.Responsible;
import edu.eci.cvds.userManagement.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides endpoints to register students and responsible persons in the system.
 * It exposes two POST endpoints:
 * - `/registerStudent` for registering a new student.
 * - `/registerResponsible` for registering a new responsible person.

 * Each method processes a registration request, interacts with the RegisterService to persist the data,
 * and returns an appropriate response with success or error messages.
 */
@RestController
@RequestMapping
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/students")
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Student studentData) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean isRegistered = registerService.registerStudent(studentData).isPresent();
            if (isRegistered) {
                return buildResponse(response, 200, "Student registered successfully.", "The student was successfully registered.");
            } else {
                return buildResponse(response, 400, "Invalid student data.", "Failed to register the student.");
            }
        } catch (Exception e) {
            return buildResponse(response, 500, "Unexpected error: " + e.getMessage(), "An error occurred while processing your request.");
        }
    }

    @PostMapping("/responsibles")
    public ResponseEntity<Map<String, Object>> createResponsible(@RequestBody Responsible newResponsible) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean isRegistered = registerService.registerResponsible(newResponsible).isPresent();
            if (isRegistered) {
                return buildResponse(response, 200, "Responsible registered successfully.", "The responsible was successfully registered.");
            } else {
                return buildResponse(response, 400, "Invalid responsible data.", "Failed to register the responsible.");
            }
        } catch (Exception e) {
            return buildResponse(response, 500, "Unexpected error: " + e.getMessage(), "An error occurred while processing your request.");
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<Map<String, Object>> createCourse(@RequestBody Course newCourse) {
        Map<String, Object> response = new HashMap<>();
        try {
            registerService.createCourse(newCourse);
            return buildResponse(response, 200, "Course created successfully.", "The course was successfully created.");
        } catch (Exception e) {
            return buildResponse(response, 400, "Invalid course data: " + e.getMessage(), "Failed to create the course.");
        }
    }

    private ResponseEntity<Map<String, Object>> buildResponse(Map<String, Object> response, int status, String developerMessage, String userMessage) {
        response.put("status", status);
        response.put("developer_message", developerMessage);
        response.put("user_message", userMessage);

        HttpStatus httpStatus;
        switch (status) {
            case 200 -> httpStatus = HttpStatus.OK;
            case 400 -> httpStatus = HttpStatus.BAD_REQUEST;
            default -> httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }
}
