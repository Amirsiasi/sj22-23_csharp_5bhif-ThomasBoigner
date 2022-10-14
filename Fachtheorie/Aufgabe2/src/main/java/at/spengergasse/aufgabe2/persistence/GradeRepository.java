package at.spengergasse.aufgabe2.persistence;

import at.spengergasse.aufgabe2.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
