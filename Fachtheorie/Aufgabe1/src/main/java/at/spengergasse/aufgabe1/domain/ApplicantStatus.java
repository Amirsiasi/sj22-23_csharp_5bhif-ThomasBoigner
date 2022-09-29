package at.spengergasse.aufgabe1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "discriminatorColumn")
@Inheritance(strategy = InheritanceType.JOINED)
@Builder
public class ApplicantStatus extends AbstractPersistable<Long> {
    public LocalDateTime RatedDate;
    public boolean Passed;
}
