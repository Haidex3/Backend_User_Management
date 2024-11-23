package edu.eci.cvds.UserManagement.controller;

import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import edu.eci.cvds.UserManagement.service.FindService;
import edu.eci.cvds.UserManagement.service.RegisterService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping
public class MigrationController {
    private final RegisterService registerService;
    private final FindService findService;

    public MigrationController(RegisterService registerService, FindService findService) {
        this.registerService = registerService;
        this.findService = findService;
    }

    @PostMapping("/migrate-data")
    public ResponseEntity<String> migrateData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("The file is empty.");
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String studentId = row.getCell(0).toString().trim();
                String studentName = row.getCell(1).toString().trim();
                String studentDocument = row.getCell(2).toString().trim();
                String studentDocumentType = row.getCell(3).toString().trim();
                String responsibleName = row.getCell(4).toString().trim();
                String responsibleDocNumber = row.getCell(5).toString().trim();
                String responsibleDocSite = row.getCell(6).toString().trim();
                String responsiblePhone = row.getCell(7).toString().trim();
                String responsibleEmail = row.getCell(8).toString().trim();
                String studentCourse = row.getCell(9).toString().trim();

                Responsible responsible = findService.findResponsibleByDocument(responsibleDocNumber);
                if (responsible == null) {
                    responsible = new Responsible(
                            responsibleDocNumber,
                            responsibleDocSite,
                            responsibleName,
                            responsiblePhone,
                            responsibleEmail
                    );
                    registerService.registerResponsible(responsible);
                }

                Student student = new Student(
                        studentId,
                        studentName,
                        studentDocument,
                        studentDocumentType,
                        studentCourse,
                        responsible.getDocument()
                );
                registerService.registerStudent(student);
            }

            return ResponseEntity.ok("Data migration completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during data migration: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }
}
