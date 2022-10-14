package at.spengergasse.aufgabe2.service;

import at.spengergasse.aufgabe2.domain.*;
import at.spengergasse.aufgabe2.domain.Class;
import at.spengergasse.aufgabe2.persistence.ExamRepository;
import at.spengergasse.aufgabe2.persistence.GradeRepository;
import at.spengergasse.aufgabe2.persistence.LessonRepository;
import at.spengergasse.aufgabe2.persistence.SubjectRepository;
import com.sun.jdi.event.ExceptionEvent;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;
    private final LessonRepository lessonRepository;

    public ClassStatistics getClassStatistics(String _class){
        List<Grade> grades = gradeRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();

        return ClassStatistics.builder()
                .className(_class)
                .positiveStudentsCount(
                        (int) grades.stream().filter(grade -> grade.getStudent().getClass().getName().equals(_class)
                        && grade.getGradeValue() != 5).count())
                .negativeStudentsCount(
                        (int) grades.stream().filter(grade -> grade.getStudent().getClass().getName().equals(_class)
                        && grade.getGradeValue() == 5).count())
                .subjectStatistics(lessons.stream().map(lesson -> SubjectStatistics.builder()
                        .shortname(lesson.getSubject().getShortname())
                        .positiveCount((int) grades.stream().filter(grade -> grade.getStudent().getClass().getName().equals(_class)
                                && grade.getGradeValue() != 5).count())
                        .positiveCount((int) grades.stream().filter(grade -> grade.getStudent().getClass().getName().equals(_class)
                                && grade.getGradeValue() == 5).count())
                        .averageGrade(grades.stream().mapToInt(Grade::getGradeValue).summaryStatistics().getAverage())
                        .build()).toList())
                .build();
    }

    public boolean tryAddRegistration(Student student, String subjectShortname, LocalDateTime date){
        // Liefert false, wenn der Gegenstand in der Klasse des Schülers nicht vorhanden ist.
        if (student.getGrades().stream().noneMatch(grade -> grade.getLesson().getSubject().equals(subjectShortname))){
            return false;
        }

        // Liefert false, wenn der Gegenstand positiv beurteilt wurde.
        if (student.getGrades().stream().filter(grade -> grade.getLesson().getSubject().equals(subjectShortname)).findFirst().get().getGradeValue() != 5){
            return false;
        }

        // Liefert false, wenn der Gegenstand bereits als Exam eingetragen wurde.
        if (student.getGrades().stream().anyMatch(grade -> subjectRepository.existsByShortname(subjectShortname))){
            return false;
        }

        // Liefert false, wenn an diesem Tag bereits eine Prüfung eingetragen wurde.
        if (examRepository.existsByDate(date)){
            return false;
        }

        Exam exam = Exam.builder()
                .date(date)
                .build();

        try{
            examRepository.save(exam);
            return true;
        }
        catch (PersistenceException pEx){
            return false;
        }
    }
}
