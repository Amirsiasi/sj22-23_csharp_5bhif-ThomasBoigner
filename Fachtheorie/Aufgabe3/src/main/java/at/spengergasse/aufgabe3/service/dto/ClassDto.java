package at.spengergasse.aufgabe3.service.dto;

import at.spengergasse.aufgabe3.domain.Class;

import java.time.LocalDateTime;

public record ClassDto(String name, String token, LocalDateTime creationTS) {

    public ClassDto(Class _class) {
        this(_class.getName(), _class.getToken(), _class.getCreationTS());
    }
}
