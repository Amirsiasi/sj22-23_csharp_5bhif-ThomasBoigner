package at.spengergasse.aufgabe3.service.dto.command;

import at.spengergasse.aufgabe3.domain.Lesson;
import at.spengergasse.aufgabe3.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateGradeCommand {
    private MutateStudentCommand student;
    private MutateLessonCommand lesson;
    private int gradeValue;
}
