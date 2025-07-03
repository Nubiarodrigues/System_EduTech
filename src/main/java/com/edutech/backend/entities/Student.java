package com.edutech.backend.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "TB_STUDENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Student extends User {

	private LocalDate dateBirth;
	private String telephone;
	private String cpf;
	private String sus;
	private String cep;
	private String address;
	private String fatherName;
	private String motherName;
	private LocalDate frequency;
	private String emailResponsable;

	@ManyToOne
	@JoinColumn(name = "classroom_id")
	@JsonBackReference
	private Classroom classroom;

}
