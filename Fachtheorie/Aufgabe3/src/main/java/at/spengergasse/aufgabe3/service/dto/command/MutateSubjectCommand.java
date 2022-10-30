package at.spengergasse.aufgabe3.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MutateSubjectCommand {
    private String shortname;
    private String longname;
}
