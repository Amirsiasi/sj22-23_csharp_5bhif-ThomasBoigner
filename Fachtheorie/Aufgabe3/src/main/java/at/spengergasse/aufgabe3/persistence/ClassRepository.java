package at.spengergasse.aufgabe3.persistence;

import at.spengergasse.aufgabe3.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    Class getByToken(String token);
    Optional<Class> getByName(String name);
}
