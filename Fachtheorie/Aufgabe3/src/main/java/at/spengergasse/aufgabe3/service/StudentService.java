package at.spengergasse.aufgabe3.service;

import at.spengergasse.aufgabe3.domain.*;
import at.spengergasse.aufgabe3.domain.Class;
import at.spengergasse.aufgabe3.foundation.TemporalValueFactory;
import at.spengergasse.aufgabe3.persistence.*;
import at.spengergasse.aufgabe3.presentation.www.UpdateBeschlussForm;
import at.spengergasse.aufgabe3.service.dto.command.MutateStudentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final LessonRepository lessonRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    private final TemporalValueFactory temporalValueFactory;
    private final TokenService tokenService;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByClass(Class _class){
        return studentRepository.getBy_class(_class);
    }

    public Optional<Student> getStudent(String id){
        return studentRepository.findStudentsByToken(id);
    }

    public Student createStudent(MutateStudentCommand command){
        Objects.requireNonNull(command, "Command must not be null!");
        Objects.requireNonNull(command.getFirstname(), "Firstname must not be null!");
        Objects.requireNonNull(command.getLastname(), "Lastname must not be null!");
        Objects.requireNonNull(command.getEmail(), "email must not be null!");
        Objects.requireNonNull(command.getGrades(), "Grades must not be null!");

        if(command.getFirstname().isEmpty() || command.getFirstname().isBlank()){
            throw new IllegalArgumentException("Firstname must not be blank or empty!");
        }

        if(command.getLastname().isEmpty() || command.getLastname().isBlank()){
            throw new IllegalArgumentException("Lastname must not be blank or empty!");
        }

        if(command.getEmail().isEmpty() || command.getEmail().isBlank()){
            throw new IllegalArgumentException("Email must not be blank or empty!");
        }

        if(command.get_class().getName().isEmpty() || command.get_class().getName().isBlank()){
            throw new IllegalArgumentException("Class name must not be blank or empty!");
        }

        if (command.getGrades().stream().anyMatch(grade -> grade.getGradeValue() > 5) || command.getGrades().stream().anyMatch(grade -> grade.getGradeValue() < 0)){
            throw new IllegalArgumentException("Grade value must be between 0 and 5!");
        }

        Class _class = classRepository.getByName(command.get_class().getName()).orElse(Class.builder()
                .name(command.get_class().getName())
                .creationTS(temporalValueFactory.now())
                .token(tokenService.createNanoId()).build());

        Student student = Student.builder()
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .email(command.getEmail())
                .conferenceDecision(command.isConferenceDecision())
                ._class(_class)
                .token(tokenService.createNanoId())
                .creationTS(temporalValueFactory.now())
                .build();

        student.setGrades(command.getGrades().stream().map(grade -> Grade.builder()
                .gradeValue(grade.getGradeValue())
                .lesson(lessonRepository.getLessonBy_class_NameAndTeacher_LastnameAndSubject_Longname(grade.getLesson().getClass().getName(), grade.getLesson().getTeacher().getLastname(), grade.getLesson().getSubject().getLongname())
                        .orElse(Lesson.builder()
                                .subject(subjectRepository.getByLongname(grade.getLesson().getSubject().getLongname())
                                        .orElse(Subject.builder()
                                                .shortname(grade.getLesson().getSubject().getShortname())
                                                .longname(grade.getLesson().getSubject().getLongname())
                                                .token(tokenService.createNanoId())
                                                .creationTS(temporalValueFactory.now())
                                                .build()))
                                .teacher(teacherRepository.getByLastnameAndFirstname(grade.getLesson().getTeacher().getLastname(), grade.getLesson().getTeacher().getFirstname())
                                        .orElse(Teacher.builder()
                                                .firstname(grade.getLesson().getTeacher().getFirstname())
                                                .lastname(grade.getLesson().getTeacher().getLastname())
                                                .email(grade.getLesson().getTeacher().getEmail())
                                                .token(tokenService.createNanoId())
                                                .creationTS(temporalValueFactory.now())
                                                .build()))
                                ._class(_class)
                                .token(tokenService.createNanoId())
                                .creationTS(temporalValueFactory.now())
                                .build()))
                .student(student)
                .token(tokenService.createNanoId())
                .creationTS(temporalValueFactory.now())
                .build()).toList());

        return studentRepository.save(student);
    }

    public Student updateBeschluss(UpdateBeschlussForm updateBeschlussForm, Student student) {
        student.setConferenceDecision(updateBeschlussForm.isConferenceDecision());
        return studentRepository.save(student);
    }
}
