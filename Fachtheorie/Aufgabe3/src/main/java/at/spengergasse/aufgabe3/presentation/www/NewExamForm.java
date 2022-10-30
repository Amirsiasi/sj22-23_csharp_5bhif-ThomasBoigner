package at.spengergasse.aufgabe3.presentation.www;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NewExamForm {
    private String date;
    private String subject;
}
