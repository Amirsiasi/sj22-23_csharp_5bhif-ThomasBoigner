package at.spengergasse.aufgabe3.service.dto.command;

import at.spengergasse.aufgabe3.domain.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MutateStudentCommand {
    private String firstname;
    private String lastname;
    private String email;
    private MutateClassCommand _class;
    private boolean conferenceDecision;
    private List<MutateGradeCommand> grades;
}
