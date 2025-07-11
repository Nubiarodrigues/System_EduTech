package com.edutech.backend.entities;

import com.edutech.backend.enuns.Shift;
import com.edutech.backend.enuns.TeachingState;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CLASSROOM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer series;
	private String identifierSeries;
	private Integer capacity;

	@Enumerated(EnumType.STRING)
	private Shift shift;

	private Integer schoolYear;

	@OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Student> students = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private TeachingState modality;
	
	@ManyToOne
	@JoinColumn(name = "coordinator_id")
	@JsonBackReference
	private Coordinator coordinatorClass;

	@ManyToMany
	@JoinTable(
			name = "classroom_discipline",
			joinColumns = @JoinColumn(name = "classroom_id"),
			inverseJoinColumns = @JoinColumn(name = "discipline_id")
	)
	@JsonManagedReference
	private List<Discipline> disciplines;

	public Classroom(Integer series, String identifierSeries, Integer capacity, Shift shift, Integer schoolYear,TeachingState modality) {
		this.series = series;
		this.identifierSeries = identifierSeries;
		this.capacity = capacity;
		this.shift = shift;
		this.schoolYear = schoolYear;
		this.modality = modality;
	}
}
