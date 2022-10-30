package at.spengergasse.aufgabe3.persistence;

import at.spengergasse.aufgabe3.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    boolean existsByDate(LocalDateTime date);
    Optional<Exam> getByToken(String token);
}
