package com.edutech.backend.entities;

import com.edutech.backend.enuns.Situation;
import com.edutech.backend.enuns.TeachingStage;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	private String cep;
	private String address;
	private String formedCourse;

	@Enumerated(EnumType.STRING)
	private TeachingStage modality;

	@OneToMany(mappedBy = "coordinatorClass", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Classroom> classroomsModality = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "coordinator_id")
	private School school;

}
