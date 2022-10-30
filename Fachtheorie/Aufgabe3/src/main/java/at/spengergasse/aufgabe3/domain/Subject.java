package at.spengergasse.aufgabe3.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Subject extends AbstractPersistable<Long> {
    private String shortname;
    private String longname;

    private String token;
    private LocalDateTime creationTS;
}
