package at.spengergasse.aufgabe2.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ClassStatistics {
    private String className;
    private int positiveStudentsCount;
    private int negativeStudentsCount;
    private List<SubjectStatistics> subjectStatistics;
}
