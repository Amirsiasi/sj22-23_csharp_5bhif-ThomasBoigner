package at.spengergasse.aufgabe3.persistence;

import at.spengergasse.aufgabe3.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
