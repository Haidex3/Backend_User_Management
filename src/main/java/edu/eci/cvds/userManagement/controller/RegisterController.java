package edu.eci.cvds.userManagement.controller;

import edu.eci.cvds.userManagement.model.Course;
import edu.eci.cvds.userManagement.service.JwtService;
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
    private final JwtService jwtService;

    public RegisterController(RegisterService registerService, JwtService jwtService) {
        this.registerService = registerService;
        this.jwtService = jwtService;
    }

    @PostMapping("/students")
    public ResponseEntity<Map<String, Object>> registerStudent(
            @RequestHeader("Authorization") String token,
            @RequestBody Student studentData) {
        Map<String, Object> response = new HashMap<>();
        try {
            jwtService.parseToken(token);
            boolean isRegistered = registerService.registerStudent(studentData).isPresent();
            if (isRegistered) {
                return buildResponse(response, 200, "Student registration successful.", "The student has been registered successfully.");
            } else {
                return buildResponse(response, 400, "Invalid student data.", "Failed to register the student. Please check your data.");
            }
        } catch (Exception e) {
            return buildResponse(response, 500, "Unexpected error occurred in register: " + e.getMessage(), "An error occurred while processing your request in register. Please try again later.");
        }
    }

    @PostMapping("/responsible")
    public ResponseEntity<Map<String, Object>> registerResponsible(
            @RequestHeader("Authorization") String token,
            @RequestBody Responsible newResponsible) {
        Map<String, Object> response = new HashMap<>();
        try {
            jwtService.parseToken(token);
            boolean isRegistered = registerService.registerResponsible(newResponsible).isPresent();
            if (isRegistered) {
                return buildResponse(response, 200, "Responsible registration successful.", "The responsible has been registered successfully.");
            } else {
                return buildResponse(response, 400, "Invalid responsible data.", "Failed to register the responsible. Please check your data.");
            }
        } catch (Exception e) {
            return buildResponse(response, 500, "Unexpected error occurred: " + e.getMessage(), "An error occurred while processing your request. Please try again later.");
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<Map<String, Object>> createCourse(
            @RequestHeader("Authorization") String token,
            @RequestBody Course newCourse) {
        Map<String, Object> response = new HashMap<>();
        try {
            jwtService.parseToken(token);
            registerService.createCourse(newCourse);
            return buildResponse(response, 200, "Course creation successful.", "The course has been created successfully.");
        } catch (Exception e) {
            return buildResponse(response, 400, "Invalid course data.", "Failed to create the course. Please check the provided data.");
        }
    }

    /**
     * Endpoint to assign a new external ID to a student and activate them.
     *
     * @param token       The authorization token from the request header.
     * @param requestData A map containing the student's ID ("studentId") and the new external ID ("newExtId").
     * @return A ResponseEntity with the operation's status, message, and details.
     */
    @PostMapping("/assignExtIdStudent")
    public ResponseEntity<Map<String, Object>> assignExtIdStudent(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, String> requestData) {
        Map<String, Object> response = new HashMap<>();
        try {
            jwtService.parseToken(token);
            String studentId = requestData.get("studentId");
            String newExtId = requestData.get("newExtId");
            if (studentId == null || newExtId == null) {
                return buildResponse(response, 400, "Missing required fields.", "Both 'studentId' and 'newExtId' must be provided.");
            }
            registerService.updateStudentExtIdAndActivate(studentId, newExtId);
            return buildResponse(response, 200, "External ID assignment successful.", "The external ID has been assigned successfully.");
        } catch (Exception e) {
            return buildResponse(response, 500, "Unexpected error occurred: " + e.getMessage(), "An error occurred while processing your request. Please try again later.");
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
