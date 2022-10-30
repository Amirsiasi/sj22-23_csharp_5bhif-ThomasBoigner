package at.spengergasse.aufgabe3.service.dto;

import at.spengergasse.aufgabe3.domain.Teacher;

import java.time.LocalDateTime;

public record TeacherDto(String firstname, String lastname, String email, String token, LocalDateTime creationTS) {
    public TeacherDto(Teacher teacher){
        this(teacher.getFirstname(), teacher.getLastname(), teacher.getEmail(), teacher.getToken(), teacher.getCreationTS());
    }
}
