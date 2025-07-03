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
	private String cep;
	private String address;
	private String formedCourse;
}
