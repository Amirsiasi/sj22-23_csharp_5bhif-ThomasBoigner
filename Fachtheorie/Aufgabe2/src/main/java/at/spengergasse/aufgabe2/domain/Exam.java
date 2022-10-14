package at.spengergasse.aufgabe2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Exam extends AbstractPersistable<Long> {
    private LocalDateTime date;
    private int examResult;
    private int newGradeValue;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "grade_id")
    private Grade grade;
}
