package com.edutech.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

	@OneToMany(mappedBy = "student")
	@JsonManagedReference
	private List<BimesterGrade> bimesters;

	@ManyToOne
	@JoinColumn(name = "classroom_id")
	@JsonBackReference
	private Classroom classroom;

}
