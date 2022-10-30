package at.spengergasse.aufgabe3.service.dto;

import at.spengergasse.aufgabe3.domain.Grade;

import java.time.LocalDateTime;

public record GradeDto(LessonDto lesson, int gradeValue, String token, LocalDateTime creationTS) {
    public GradeDto(Grade grade){
        this(new LessonDto(grade.getLesson()), grade.getGradeValue(), grade.getToken(), grade.getCreationTS());
    }
}
