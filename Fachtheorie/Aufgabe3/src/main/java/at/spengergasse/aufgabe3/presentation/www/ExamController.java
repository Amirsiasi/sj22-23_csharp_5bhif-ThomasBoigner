package at.spengergasse.aufgabe3.presentation.www;

import at.spengergasse.aufgabe3.domain.*;
import at.spengergasse.aufgabe3.persistence.ExamRepository;
import at.spengergasse.aufgabe3.service.ClassService;
import at.spengergasse.aufgabe3.service.ExamService;
import at.spengergasse.aufgabe3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping(ExamController.BASE_URL)
public class ExamController {

    private final StudentService studentService;
    private final ClassService classService;
    private final ExamService examService;

    public static final String BASE_URL = "/Pruefung/Anmeldung";
    public static final String PATH_VAR_ID = "/{id}";

    @GetMapping(PATH_VAR_ID)
    public String showExamSignup(Model model, @PathVariable String id){
        Student student = studentService.getStudent(id).orElseThrow(() -> new IllegalArgumentException(String.format("Can't find Student %s!", id)));
        model.addAttribute("student", student);

        List<Exam> exams = examService.getExams().stream().filter(exam -> exam.getGrade().getStudent().equals(student)).toList();
        model.addAttribute("exams", exams);

        List<Subject> subjects = student.getGrades().stream().filter(grade -> grade.getGradeValue() == 5).map(Grade::getLesson).map(Lesson::getSubject).toList();
        model.addAttribute("subjects", subjects);

        model.addAttribute("newExam", new NewExamForm());
        return "exam/examSignup";
    }

    @PostMapping(PATH_VAR_ID)
    public String handleExamForm(@ModelAttribute(name="newExam") NewExamForm newExamForm, @PathVariable String id){
        Student student = studentService.getStudent(id).orElseThrow(() -> new IllegalArgumentException("Student can't be found!"));
        examService.createExam(newExamForm, student);
        return "redirect:"+ ConferenceController.BASE_URL + "/Klasse/" + student.get_class().getToken();
    }
}
