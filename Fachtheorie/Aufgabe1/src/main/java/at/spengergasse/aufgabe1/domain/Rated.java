package at.spengergasse.aufgabe1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor

@Entity
public class Rated extends ApplicantStatus{
}
