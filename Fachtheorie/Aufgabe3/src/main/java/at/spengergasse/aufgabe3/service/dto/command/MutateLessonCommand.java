package at.spengergasse.aufgabe3.service.dto.command;

import at.spengergasse.aufgabe3.domain.Class;
import at.spengergasse.aufgabe3.domain.Subject;
import at.spengergasse.aufgabe3.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateLessonCommand {
    private MutateSubjectCommand subject;
    private MutateClassCommand _class;
    private MutateTeacherCommand teacher;
}
