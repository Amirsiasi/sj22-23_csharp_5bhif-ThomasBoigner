package at.spengergasse.aufgabe1.persistence;

import at.spengergasse.aufgabe1.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
