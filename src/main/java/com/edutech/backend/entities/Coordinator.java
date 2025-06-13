package com.edutech.backend.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.edutech.backend.enuns.Situation;
import com.edutech.backend.enuns.TeachingState;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "TB_COORDINATOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Coordinator extends User {

	private String cpf;
	private String telephone;
	
	@Enumerated(EnumType.STRING)
	private Situation status;
	
	private LocalDate dateBirth;
	private String rg;
	private String address;
	private String matriculation;
	private String formedCourse;
	
	@Enumerated(EnumType.STRING)
	private TeachingState modality;
	
	@OneToMany(mappedBy = "coordinatorClass", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Classroom> classroomsModality = new ArrayList<>();
}
