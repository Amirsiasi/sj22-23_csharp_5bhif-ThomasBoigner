package at.spengergasse.aufgabe3.foundation;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TemporalValueFactory {
    public LocalDateTime now(){
        return LocalDateTime.now();
    }
}
