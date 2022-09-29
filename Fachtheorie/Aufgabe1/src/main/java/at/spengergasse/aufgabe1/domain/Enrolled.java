package at.spengergasse.aufgabe1.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Enrolled extends ApplicantStatus{

}
