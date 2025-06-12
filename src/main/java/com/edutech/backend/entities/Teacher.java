package com.edutech.backend.entities;

import java.time.LocalDate;

import com.edutech.backend.enuns.Situation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "TB_TEACHER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Teacher extends User {

	private String cpf;
	private String telephone;

	@Enumerated(EnumType.STRING)
	private Situation status;

	private LocalDate dateBirth;
	private Integer workloadTotal;
	private String rg;
	private String address;
	private String matriculation;
	private String formedCourse;

	public Teacher(String name, String cpf, String telephone, Situation status, LocalDate dateBirth,Integer workloadTotal, String rg, String address, String matriculation, String formedCourse) {
		super(name);
		this.cpf = cpf;
		this.telephone = telephone;
		this.status = status;
		this.dateBirth = dateBirth;
		this.workloadTotal = workloadTotal;
		this.rg = rg;
		this.address = address;
		this.matriculation = matriculation;
		this.formedCourse = formedCourse;
	}
}
