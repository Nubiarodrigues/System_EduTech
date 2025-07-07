package com.edutech.backend.entities;

import com.edutech.backend.enuns.Situation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

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

	@OneToMany(mappedBy = "teacher")
	@JsonManagedReference
	@JsonIgnore
	private List<Discipline> disciplines;
}
