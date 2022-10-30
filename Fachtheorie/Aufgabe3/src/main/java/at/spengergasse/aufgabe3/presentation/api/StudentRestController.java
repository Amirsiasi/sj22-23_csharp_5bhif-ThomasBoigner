package at.spengergasse.aufgabe3.presentation.api;

import at.spengergasse.aufgabe3.domain.Class;
import at.spengergasse.aufgabe3.domain.Student;
import at.spengergasse.aufgabe3.service.StudentService;
import at.spengergasse.aufgabe3.service.dto.ClassDto;
import at.spengergasse.aufgabe3.service.dto.StudentDto;
import at.spengergasse.aufgabe3.service.dto.command.MutateClassCommand;
import at.spengergasse.aufgabe3.service.dto.command.MutateStudentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping(StudentRestController.BASE_URL)
public class StudentRestController {

    private final StudentService studentService;

    public static final String BASE_URL = "/api/student";
    public static final String PATH_INDEX = "/";

    @GetMapping({"", PATH_INDEX})
    public HttpEntity<List<StudentDto>> getStudents(){
        List<Student> students = studentService.getStudents();
        return (students.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(students.stream().map(StudentDto::new).toList());
    }

    @PostMapping({PATH_INDEX, ""})
    public HttpEntity<StudentDto> createStudent(@RequestBody MutateStudentCommand command){
        Student student = studentService.createStudent(command);
        return ResponseEntity.ok(new StudentDto(student));
    }
}
