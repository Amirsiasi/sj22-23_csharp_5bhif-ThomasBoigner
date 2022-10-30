package at.spengergasse.aufgabe3.service;

import at.spengergasse.aufgabe3.domain.Class;
import at.spengergasse.aufgabe3.foundation.NanoIdFactory;
import at.spengergasse.aufgabe3.foundation.TemporalValueFactory;
import at.spengergasse.aufgabe3.persistence.ClassRepository;
import at.spengergasse.aufgabe3.service.dto.command.MutateClassCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor

@Service
public class ClassService {
    private final ClassRepository classRepository;
    private final TokenService tokenService;
    private final TemporalValueFactory temporalValueFactory;

    public List<Class> getClasses(){
        return classRepository.findAll();
    }

    public Class getClass(String id){
        return classRepository.getByToken(id);
    }

    public Class createClass(MutateClassCommand command){
        Objects.requireNonNull(command, "Command must not be null!");
        Objects.requireNonNull(command.getName(), "Name must not be null!");

        if(command.getName().isEmpty() || command.getName().isEmpty()){
            throw new IllegalArgumentException("Class name must not be null or empty!");
        }

        return classRepository.save(Class.builder()
                .name(command.getName())
                .token(tokenService.createNanoId())
                .creationTS(temporalValueFactory.now())
                .build());
    }
}
