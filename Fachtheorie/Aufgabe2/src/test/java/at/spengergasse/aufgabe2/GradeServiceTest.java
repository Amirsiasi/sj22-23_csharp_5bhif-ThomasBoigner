package at.spengergasse.aufgabe2;

import at.spengergasse.aufgabe2.domain.*;
import at.spengergasse.aufgabe2.domain.Class;
import at.spengergasse.aufgabe2.persistence.StudentRepository;
import at.spengergasse.aufgabe2.service.GradeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GradeServiceTest {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void TryAddRegistrationReturnsFalseIfSubjectDoesNotExist(){
        // given
        Student student = Student.builder()
                .firstname("Thomas")
                .lastname("Boigner")
                .email("BOI19439@spengergasse.at")
                .conferenceDecision(false)
                ._class(Class.builder()
                        .name("5BHIF")
                        .build())
                .grades(List.of(Grade.builder()
                                .GradeValue(5)
                                .lesson(Lesson.builder()
                                        .subject(Subject.builder()
                                                .longname("Mathematik")
                                                .shortname("AM")
                                                .build())
                                        .build())
                        .build()))
                .build();
        studentRepository.save(student);
        String subject = "D";

        //when
        boolean result = gradeService.tryAddRegistration(student, subject, LocalDateTime.MIN);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void TryAddRegistrationReturnsFalseIfSubjectIsNotNegative(){
        // given
        Student student = Student.builder()
                .firstname("Thomas")
                .lastname("Boigner")
                .email("BOI19439@spengergasse.at")
                .conferenceDecision(false)
                ._class(Class.builder()
                        .name("5BHIF")
                        .build())
                .grades(List.of(Grade.builder()
                        .GradeValue(1)
                        .lesson(Lesson.builder()
                                .subject(Subject.builder()
                                        .longname("Deutsch")
                                        .shortname("D")
                                        .build())
                                .build())
                        .build()))
                .build();
        studentRepository.save(student);

        String subject = "D";

        // when
        boolean result = gradeService.tryAddRegistration(student, subject, LocalDateTime.MIN);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void TryAddRegistrationReturnsFalseIfExamExists(){
        // given
        Student student = Student.builder()
                .firstname("Thomas")
                .lastname("Boigner")
                .email("BOI19439@spengergasse.at")
                .conferenceDecision(false)
                ._class(Class.builder()
                        .name("5BHIF")
                        .build())
                .grades(List.of(Grade.builder()
                        .GradeValue(5)
                        .lesson(Lesson.builder()
                                .subject(Subject.builder()
                                        .longname("Deutsch")
                                        .shortname("D")
                                        .build())
                                .build())
                        .build()))
                .build();
        studentRepository.save(student);

        String subject = "D";

        // when
        boolean result = gradeService.tryAddRegistration(student, subject, LocalDateTime.MIN);

        // then
        assertThat(result).isFalse();
    }
}
