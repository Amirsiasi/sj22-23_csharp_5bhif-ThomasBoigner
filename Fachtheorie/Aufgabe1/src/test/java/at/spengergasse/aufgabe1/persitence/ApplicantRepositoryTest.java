package at.spengergasse.aufgabe1.persitence;

import at.spengergasse.aufgabe1.domain.*;
import at.spengergasse.aufgabe1.persistence.ApplicantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ApplicantRepositoryTest {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Test
    void AddApplicantSuccessTest(){
        //given
        Applicant applicant = Applicant.builder()
                .vorname("thomas")
                .nachname("Boigner")
                .geburtsdatum(LocalDate.of(2004, 02, 04))
                .email("BOI19349@spengergasse.at")
                .telefonnummer("+43 664 123456")
                .geschlecht(Gender.MALE)
                .uploads(List.of(Upload.builder()
                        .zeitstempel(LocalDateTime.of(2021, 5, 10, 12, 55, 3))
                        .build(),
                        Upload.builder()
                        .zeitstempel(LocalDateTime.of(2022, 6, 15, 14, 16, 33))
                        .build()))
                .department(Department.builder()
                        .name("Hohere Informatik")
                        .tasks(List.of(Task.builder()
                                .text("cool Task")
                                .dateFrom(LocalDate.of(2021, 8, 26))
                                .build()))
                        .build())
                .applicantStatus(ApplicantStatus.builder()
                        .Passed(true)
                        .RatedDate(LocalDateTime.of(2021, 6, 21, 13, 25, 40))
                        .build())
                .build();

        //when
        applicantRepository.save(applicant);
        Applicant savedApplicant = applicantRepository.getReferenceById(applicant.getId());

        //then
        assertThat(applicant).isEqualTo(savedApplicant);
    }
}
