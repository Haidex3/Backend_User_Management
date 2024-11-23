package edu.eci.cvds.userManagement.controller;

import edu.eci.cvds.userManagement.model.Course;
import edu.eci.cvds.userManagement.model.Grade;
import edu.eci.cvds.userManagement.model.Responsible;
import edu.eci.cvds.userManagement.service.FindService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * Endpoint to obtain the courses associated with a degree.
     *
     * @param gradeName The name of the grade.
     * @return List of course names.
     */
    @GetMapping("/{gradeName}/courses")
    public List<String> getCoursesByGrade(@PathVariable String gradeName) {
        List<Course> courses = findService.findCoursesByGradeName(gradeName);
        return courses.stream().map(Course::getName).toList();
    }


    @GetMapping("/findResponsibleByDocument")
    public Responsible findResponsibleByDocument(
            @RequestParam String responsibleDocNumber)  {
        return findService.findResponsibleByDocument(responsibleDocNumber);
    }

    @GetMapping("/findCoursesByGrade")
    public List<Course> findCoursesByGrade(
            @RequestParam String gradeName){
        return findService.findCoursesByGradeName(gradeName);
    }

}
