package at.spengergasse.aufgabe3.domain;

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
public class Lesson extends AbstractPersistable<Long> {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinColumn(name = "class_id")
    private Class _class;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private String token;
    private LocalDateTime creationTS;
}
