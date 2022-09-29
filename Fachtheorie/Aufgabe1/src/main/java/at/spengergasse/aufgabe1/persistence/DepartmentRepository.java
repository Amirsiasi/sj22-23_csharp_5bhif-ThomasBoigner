package at.spengergasse.aufgabe1.persistence;

import at.spengergasse.aufgabe1.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
