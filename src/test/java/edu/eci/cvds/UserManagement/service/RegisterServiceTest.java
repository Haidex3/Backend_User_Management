package edu.eci.cvds.UserManagement.service;

import edu.eci.cvds.UserManagement.model.Responsible;
import edu.eci.cvds.UserManagement.model.Student;
import edu.eci.cvds.UserManagement.repositories.ResponsibleRepository;
import edu.eci.cvds.UserManagement.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class RegisterServiceTest {

    @Mock
    private ResponsibleRepository responsibleRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private RegisterService registerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
/*
    @Test
    public void testRegisterStudentSuccess() throws SQLException {
        Student student = new Student("1", "John Doe", "Computer Science", 2023, null, "Father");

        Optional<Student> result = registerService.registerStudent(student);

        assertTrue(result.isPresent());
        assertEquals(student, result.get());
    }

    @Test
    public void testRegisterStudentFailure() {
        Student student = new Student("1", "John Doe", "Computer Science", 2023, null, "Father");

        Optional<Student> result = registerService.registerStudent(student);

        //assertTrue(result.isEmpty());
    }

    @Test
    public void testRegisterResponsibleSuccess() throws SQLException {
        Responsible responsible = new Responsible(123456L, "ID", "Jane Doe", "555-1234", "jane.doe@example.com", "123 Main St");
        // Call the method
        Optional<Responsible> result = registerService.registerResponsible(responsible);

        assertTrue(result.isPresent());
        assertEquals(responsible, result.get());
    }

    @Test
    public void testRegisterResponsibleFailure() throws SQLException {
        Responsible responsible = new Responsible(123456L, "ID", "Jane Doe", "555-1234", "jane.doe@example.com", "123 Main St");

        doThrow(new RuntimeException("Error saving responsible")).when(responsibleRepository).saveResponsible(any(Responsible.class));

        Optional<Responsible> result = registerService.registerResponsible(responsible);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindResponsibleByDocumentSuccess() throws SQLException {
        // Create a mock Responsible object
        Responsible responsible = new Responsible(123456L, "ID", "Jane Doe", "555-1234", "jane.doe@example.com", "123 Main St");
        Long docNumber = 123456L;

        when(responsibleRepository.findResponsibleByDocument(docNumber)).thenReturn(responsible);

        Optional<Responsible> result = registerService.findResponsibleByDocument(docNumber);

        assertTrue(result.isPresent());
        assertEquals(responsible, result.get());
    }

    @Test
    public void testFindResponsibleByDocumentNotFound() throws SQLException {
        Long docNumber = 123456L;

        when(responsibleRepository.findResponsibleByDocument(docNumber)).thenReturn(null);

        Optional<Responsible> result = registerService.findResponsibleByDocument(docNumber);

        assertTrue(result.isEmpty());
    }*/
}
