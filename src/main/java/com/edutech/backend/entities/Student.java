package com.edutech.backend.entities;

import java.time.LocalDate;

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
	private String address;
	private String fatherName;
	private String motherName;
	private LocalDate frequency;
	private String emailResponsable;

	@ManyToOne
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;

	public Student(String name, LocalDate dateBirth, String cpf, String sus, String address, String telephone,String emailResponsable, String motherName, String fatherName, Classroom classroom) {
		super(name);
		this.dateBirth = dateBirth;
		this.cpf = cpf;
		this.sus = sus;
		this.address = address;
		this.telephone = telephone;
		this.emailResponsable = emailResponsable;
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.classroom = classroom;
	}

}
