package at.spengergasse.aufgabe3.persistence;

import at.spengergasse.aufgabe3.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> getByLastnameAndFirstname(String Lastname, String Firstname);
}
