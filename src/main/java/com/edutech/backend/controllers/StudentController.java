package com.edutech.backend.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.dtos.StudentResponseDTO;
import com.edutech.backend.entities.Student;
import com.edutech.backend.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentService serviceStudent;

	public StudentController(StudentService serviceStudent) {
		this.serviceStudent = serviceStudent;
	}

	@GetMapping
	public ResponseEntity<List<StudentResponseDTO>> findAll() {
		List<StudentResponseDTO> students = serviceStudent.findAll();
		return ResponseEntity.ok(students);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentResponseDTO> findByIdStudent(@PathVariable Long id) {
		Student wanted = serviceStudent.findById(id);
		StudentResponseDTO response = new StudentResponseDTO(wanted);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity<StudentResponseDTO> insert(@RequestBody @Valid StudentRequestDTO dto) {
		Student newStudent = serviceStudent.createStudent(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newStudent.getId())
				.toUri();
		StudentResponseDTO response = new StudentResponseDTO(newStudent);
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentResponseDTO> update(@PathVariable Long id, @RequestBody @Valid StudentRequestDTO dto) {
		Student current = serviceStudent.updateStudent(id, dto);
		StudentResponseDTO response = new StudentResponseDTO(current);
		return ResponseEntity.ok(response);
	}

}
