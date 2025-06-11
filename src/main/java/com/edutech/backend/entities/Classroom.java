package com.edutech.backend.entities;

import java.util.List;

import com.edutech.backend.enuns.Shift;
import com.edutech.backend.enuns.TeachingState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	@OneToMany(mappedBy = "classroom")
	private List<Student> students;

	@Enumerated(EnumType.STRING)
	private TeachingState modality;

}
