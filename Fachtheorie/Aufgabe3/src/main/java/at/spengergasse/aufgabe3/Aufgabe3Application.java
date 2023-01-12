package at.spengergasse.aufgabe3;

import at.spengergasse.aufgabe3.domain.Grade;
import at.spengergasse.aufgabe3.service.StudentService;
import at.spengergasse.aufgabe3.service.dto.command.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class Aufgabe3Application {

	public static void main(String[] args) {
		SpringApplication.run(Aufgabe3Application.class, args);
	}

}
