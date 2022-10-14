package at.spengergasse.aufgabe2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Teacher extends AbstractPersistable<Long> {
    private String firstname;
    private String lastname;
    private String email;
}
