package at.spengergasse.aufgabe1.persitence;

import at.spengergasse.aufgabe1.domain.*;
import at.spengergasse.aufgabe1.persistence.ApplicantStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ApplicantStatusRepositoryTest {

    @Autowired
    private ApplicantStatusRepository applicantStatusRepository;

    @Test
    void AddApplicantWithApplicantStatusSuccessTest(){
        //given
        ApplicantStatus applicantStatus = Rated.builder()
                .Passed(true)
                .RatedDate(LocalDateTime.of(2021, 6, 21, 13, 25, 40))
                .build();

        //when
        applicantStatusRepository.save(applicantStatus);
        ApplicantStatus savedApplicantStatus = applicantStatusRepository.getReferenceById(applicantStatus.getId());

        //then
        assertThat(applicantStatus).isEqualTo(savedApplicantStatus);
    }
}
