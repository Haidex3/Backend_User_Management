package edu.eci.cvds.UserManagement.controller;

import edu.eci.cvds.UserManagement.service.RegisterService;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * This class is responsible for handling data migration requests. It receives a file containing student and responsible data,
 * processes each line, and migrates the data to the appropriate service methods.
 * The data migration involves reading a CSV file containing student and responsible information, checking if a responsible person
 * already exists in the system by their document type and number, and either using the existing responsible or registering a new one.
 * Then, the student data is processed and stored accordingly.
 * It exposes a POST endpoint (`/migrate-data`) where the file containing migration data is uploaded.
 * The endpoint will read the file, validate its contents, and call the appropriate services to register students and responsible persons.
 */
@RestController
@RequestMapping
public class MigrationController {
    private final RegisterService registerService;

    /**
     * Constructor to initialize the MigrationController with the required RegisterService dependency.
     *
     * @param registerService the service responsible for handling the registration of students and responsible persons.
     */
    public MigrationController(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * Endpoint to handle the migration of student and responsible data from a provided CSV file.
     * The file is processed line by line, and each line contains data for a student and their responsible person.
     * The method processes the data as follows:
     * 1. Reads the CSV file from the request.
     * 2. Extracts data for both the student and the responsible person.
     * 3. Checks if the responsible person already exists in the system based on their document type and number.
     * 4. Registers a new responsible person if none exists.
     * 5. Registers the student and associates them with the responsible person.
     *
     * @param file the file containing the student and responsible data.
     * @return a message indicating whether the migration was successful or an error occurred.
     */
    @PostMapping("/migrate-data")
    public String migrateData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "The file is empty.";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String studentId = data[0];
                String studentName = data[1];
                String studentCourse = data[2];
                int studentYear = Integer.parseInt(data[3]);
                String responsibleName = data[4];
                String responsibleDocSite = data[5];
                Long responsibleDocNumber = Long.valueOf(data[6]);
                String responsibleEmail = data[7];
                String responsiblePhone = data[8];
                String responsibleAddress = data[9];
                String relationWithStudent = data[10];

                Optional<Responsible> existingResponsible;
                Responsible responsible;
                try {
                    existingResponsible = registerService.findResponsibleByDocument(responsibleDocNumber);
                    if (existingResponsible.isPresent()) {
                        responsible = existingResponsible.get();
                    } else {
                        responsible = new Responsible(responsibleDocNumber,responsibleDocSite,responsibleName,responsiblePhone,responsibleEmail,responsibleAddress);

                        registerService.registerResponsible(responsible);
                    }

                    Student student = new Student(studentId, studentName, studentCourse, studentYear, responsible, relationWithStudent);

                    registerService.registerStudent(student);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            return "Data migration completed successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred during data migration: " + e.getMessage();
        }
    }
}
