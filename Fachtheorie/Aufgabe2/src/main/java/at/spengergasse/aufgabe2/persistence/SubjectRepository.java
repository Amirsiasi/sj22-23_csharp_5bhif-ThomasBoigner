package at.spengergasse.aufgabe2.persistence;

import at.spengergasse.aufgabe2.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByShortname(String shortname);
}
