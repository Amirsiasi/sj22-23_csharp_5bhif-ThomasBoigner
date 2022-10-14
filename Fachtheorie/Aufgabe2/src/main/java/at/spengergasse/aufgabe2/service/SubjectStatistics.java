package at.spengergasse.aufgabe2.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SubjectStatistics {
    private String shortname;
    private int positiveCount;
    private int negativeCount;
    private double averageGrade;
}
