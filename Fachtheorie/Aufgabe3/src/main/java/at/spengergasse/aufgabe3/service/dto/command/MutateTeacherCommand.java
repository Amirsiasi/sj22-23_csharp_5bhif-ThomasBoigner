package at.spengergasse.aufgabe3.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MutateTeacherCommand {
    private String firstname;
    private String lastname;
    private String email;
}
