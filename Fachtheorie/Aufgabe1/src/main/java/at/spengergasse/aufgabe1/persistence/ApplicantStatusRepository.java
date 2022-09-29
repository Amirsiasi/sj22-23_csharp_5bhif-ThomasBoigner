package at.spengergasse.aufgabe1.persistence;

import at.spengergasse.aufgabe1.domain.ApplicantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantStatusRepository extends JpaRepository<ApplicantStatus, Long> {

}
