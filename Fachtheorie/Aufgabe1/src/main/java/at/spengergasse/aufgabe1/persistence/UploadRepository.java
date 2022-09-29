package at.spengergasse.aufgabe1.persistence;

import at.spengergasse.aufgabe1.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {

}
