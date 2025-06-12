package com.edutech.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.backend.dtos.TeacherRequestDTO;
import com.edutech.backend.dtos.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.services.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

	private final TeacherService serviceTeacher;

	public TeacherController(TeacherService serviceTeacher) {
		this.serviceTeacher = serviceTeacher;
	}

	@GetMapping
	public ResponseEntity<List<TeacherResponseDTO>> findAll() {
		List<TeacherResponseDTO> teachers = serviceTeacher.findAll();
		return ResponseEntity.ok(teachers);
	}

	@PostMapping
	public ResponseEntity<TeacherResponseDTO> create(@RequestBody @Valid TeacherRequestDTO dto) {
		Teacher newTeacher = serviceTeacher.createTeacher(dto);
		TeacherResponseDTO response = new TeacherResponseDTO(newTeacher);
		return ResponseEntity.ok(response);
	}
}
