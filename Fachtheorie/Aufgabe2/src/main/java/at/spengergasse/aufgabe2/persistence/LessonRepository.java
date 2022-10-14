package at.spengergasse.aufgabe2.persistence;

import at.spengergasse.aufgabe2.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
