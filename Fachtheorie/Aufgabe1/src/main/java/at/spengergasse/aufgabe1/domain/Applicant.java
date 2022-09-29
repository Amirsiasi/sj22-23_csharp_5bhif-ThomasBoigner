package at.spengergasse.aufgabe1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Applicant extends AbstractPersistable<Long> {

    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;
    private String email;
    private String telefonnummer;
    @Enumerated(EnumType.STRING)
    private Gender geschlecht;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "applicant")
    public List<Upload> uploads;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "applicant_status_id")
    private ApplicantStatus applicantStatus;
}
