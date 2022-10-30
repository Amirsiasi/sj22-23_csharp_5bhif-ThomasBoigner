package at.spengergasse.aufgabe3.presentation.www;

import at.spengergasse.aufgabe3.domain.Class;
import at.spengergasse.aufgabe3.domain.Grade;
import at.spengergasse.aufgabe3.domain.Lesson;
import at.spengergasse.aufgabe3.domain.Student;
import at.spengergasse.aufgabe3.service.ClassService;
import at.spengergasse.aufgabe3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping(ConferenceController.BASE_URL)
public class ConferenceController {

    private final ClassService classService;
    private final StudentService studentService;

    public static final String BASE_URL = "/Konferenz";
    public static final String PATH_VAR_ID = "/{id}";
    public static final String ROUTE_INDEX = "/";
    public static final String INDEX = "/Index";
    public static final String ROUTE_SHOW_CLASS = "/Klasse" + PATH_VAR_ID;
    public static final String ROUTE_BESCHLUSS = "/Beschluss" + PATH_VAR_ID;

    @GetMapping({INDEX, ROUTE_INDEX})
    public String index(Model model){
        var classes = classService.getClasses();
        model.addAttribute("classes", classes);
        return "conference/index";
    }

    @GetMapping(ROUTE_SHOW_CLASS)
    public String showClass(Model model, @PathVariable String id){
        Class selectedClass = classService.getClass(id);
        model.addAttribute("class", selectedClass);
        List<Student> students = studentService.getStudentsByClass(selectedClass);
        model.addAttribute("students", students);
        List<Boolean> beschluss = students.stream().map(student -> student.getGrades().stream().filter(grade -> grade.getGradeValue() == 5).count() > 1).toList();
        model.addAttribute("beschluss", beschluss);
        List<Boolean> pruefungAnmelden = students.stream().map(student -> student.getGrades().stream().anyMatch(grade -> grade.getGradeValue() == 5)).toList();
        model.addAttribute("pruefungAnmelden", pruefungAnmelden);
        return "conference/showClass";
    }

    @GetMapping(ROUTE_BESCHLUSS)
    public String showBeschlussForm(Model model, @PathVariable String id){
        Student student = studentService.getStudent(id).orElseThrow(() -> new IllegalArgumentException(String.format("Can not find student %s", id)));
        model.addAttribute("selectedStudent", student);
        model.addAttribute("subjects", student.getGrades().stream().filter(grade -> grade.getGradeValue() == 5).map(Grade::getLesson).map(Lesson::getSubject).toList());
        model.addAttribute("updateBeschluss", new UpdateBeschlussForm());
        return "conference/changeBeschluss";
    }

    @PostMapping(ROUTE_BESCHLUSS)
    public String handleBeschlussForm(@ModelAttribute(name = "updateBeschluss")UpdateBeschlussForm updateBeschlussForm, @PathVariable String id){
        Student student = studentService.getStudent(id).orElseThrow(() -> new IllegalArgumentException("Student can't be found!"));
        studentService.updateBeschluss(updateBeschlussForm, student);
        return "redirect:"+ BASE_URL + "/Klasse/" + student.get_class().getToken();
    }
}
