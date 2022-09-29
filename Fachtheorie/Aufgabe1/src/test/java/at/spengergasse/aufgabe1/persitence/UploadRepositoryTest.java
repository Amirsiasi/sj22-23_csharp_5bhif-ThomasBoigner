package at.spengergasse.aufgabe1.persitence;

import at.spengergasse.aufgabe1.domain.Upload;
import at.spengergasse.aufgabe1.persistence.UploadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UploadRepositoryTest {
    @Autowired
    private UploadRepository uploadRepository;

    @Test
    void AddUploadSuccessTest(){
        //given
        Upload upload = Upload.builder()
                .zeitstempel(LocalDateTime.of(2021, 5, 10, 12, 55, 3))
                .build();

        //when
        uploadRepository.save(upload);
        Upload savedUpload = uploadRepository.getReferenceById(upload.getId());

        //then
        assertThat(upload).isEqualTo(savedUpload);
    }
}
