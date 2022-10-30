package at.spengergasse.aufgabe3.service.dto;

import at.spengergasse.aufgabe3.domain.Subject;

import java.time.LocalDateTime;

public record SubjectDto(String shortname, String longname, String token, LocalDateTime creationTS) {
    public SubjectDto(Subject subject){
        this(subject.getShortname(), subject.getLongname(), subject.getToken(), subject.getCreationTS());
    }
}
