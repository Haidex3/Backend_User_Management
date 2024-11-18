package edu.eci.cvds.UserManagement.controller;
import edu.eci.cvds.UserManagement.model.Course;
import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import edu.eci.cvds.UserManagement.service.FindService;
import edu.eci.cvds.UserManagement.service.RegisterService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

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
    public String migrateData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "The file is empty.";
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String studentId = String.valueOf(row.getCell(0).getNumericCellValue());
                String studentName = row.getCell(1).getStringCellValue();
                String studentDocument = String.valueOf(row.getCell(2).getNumericCellValue());
                String studentDocumentType = row.getCell(3).getStringCellValue();
                String responsibleName = row.getCell(4).getStringCellValue();
                String responsibleDocNumber = String.valueOf(row.getCell(5).getNumericCellValue());
                String responsibleDocSite = row.getCell(6).getStringCellValue();
                String responsiblePhone = String.valueOf(row.getCell(7).getNumericCellValue());;
                String responsibleEmail = row.getCell(8).getStringCellValue();
                String studentCourse = row.getCell(9).getStringCellValue();

                Optional<Responsible> existingResponsible = Optional.ofNullable(findService.findResponsibleByDocument(responsibleDocNumber));
                Responsible responsible;
                if (existingResponsible.isPresent()) {
                    responsible = existingResponsible.get();
                } else {
                    responsible = new Responsible(responsibleDocNumber, responsibleDocSite, responsibleName, responsiblePhone, responsibleEmail);
                    registerService.registerResponsible(responsible);
                }

                Student student = new Student(studentId, studentName, studentDocument, studentDocumentType, studentCourse,responsibleDocNumber);
                registerService.registerStudent(student);
            }

            return "Data migration completed successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred during data migration: " + e.getMessage();
        }
    }
}
