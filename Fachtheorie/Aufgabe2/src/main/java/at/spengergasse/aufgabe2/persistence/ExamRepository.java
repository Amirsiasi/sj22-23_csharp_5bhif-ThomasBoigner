package at.spengergasse.aufgabe2.persistence;

import at.spengergasse.aufgabe2.domain.Exam;
import at.spengergasse.aufgabe2.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    boolean existsByDate(LocalDateTime date);
}
