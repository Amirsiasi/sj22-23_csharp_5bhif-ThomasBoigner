package at.spengergasse.aufgabe3.service;

import at.spengergasse.aufgabe3.domain.Exam;
import at.spengergasse.aufgabe3.domain.Grade;
import at.spengergasse.aufgabe3.domain.Student;
import at.spengergasse.aufgabe3.foundation.TemporalValueFactory;
import at.spengergasse.aufgabe3.persistence.ExamRepository;
import at.spengergasse.aufgabe3.persistence.GradeRepository;
import at.spengergasse.aufgabe3.presentation.www.NewExamForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final GradeRepository gradeRepository;


    private final TemporalValueFactory temporalValueFactory;
    private final TokenService tokenService;

    public List<Exam> getExams(){
        return examRepository.findAll();
    }

    public Optional<Exam> getExam(String id){
        return examRepository.getByToken(id);
    }

    public Exam createExam(NewExamForm newExamForm, Student student){
        Objects.requireNonNull(newExamForm, "Examform must not be null!");
        Objects.requireNonNull(student, "Student must not be null!");
        Objects.requireNonNull(newExamForm.getDate(), "Date must not be null!");
        Objects.requireNonNull(newExamForm.getSubject(), "Subject must not be null!");

        if(newExamForm.getDate().isEmpty() || newExamForm.getDate().isBlank()){
            throw new IllegalArgumentException("Date must not be null or empty!");
        }

        if (newExamForm.getSubject().isEmpty() || newExamForm.getSubject().isBlank()){
            throw new IllegalArgumentException("Date must not be null or empty!");
        }

        Grade grade = gradeRepository.findAll().stream().filter(grade1 -> grade1.getStudent().equals(student)
                && grade1.getGradeValue() == 5
                && grade1.getLesson().getSubject().getShortname().equals(newExamForm.getSubject())).findFirst().orElseThrow(() -> new IllegalArgumentException("Can't find grade!"));

        LocalDateTime date = LocalDateTime.parse(newExamForm.getDate());
        return examRepository.save(Exam.builder()
                        .newGradeValue(0)
                        .grade(grade)
                        .examResult(0)
                        .date(date)
                        .token(tokenService.createNanoId())
                        .creationTS(temporalValueFactory.now())
                .build());
    }
}
