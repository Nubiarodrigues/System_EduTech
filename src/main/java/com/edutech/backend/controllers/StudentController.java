package com.edutech.backend.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edutech.backend.dtos.student.StudentRequestDTO;
import com.edutech.backend.dtos.student.StudentResponseDTO;
import com.edutech.backend.entities.Student;
import com.edutech.backend.mapper.StudentMapper;
import com.edutech.backend.services.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService serviceStudent;
	private final StudentMapper mapperStudent;

	@GetMapping
	public ResponseEntity<List<StudentResponseDTO>> findAll() {
		List<StudentResponseDTO> students = serviceStudent.findAll();
		return ResponseEntity.ok(students);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentResponseDTO> findById(@PathVariable Long id) {
		Student student = serviceStudent.findById(id);
		StudentResponseDTO response = mapperStudent.toResponseDTO(student);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity<StudentResponseDTO> create(@RequestBody @Valid StudentRequestDTO dto) {
		Student newStudent = serviceStudent.createStudent(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newStudent.getId())
				.toUri();
		StudentResponseDTO response = mapperStudent.toResponseDTO(newStudent);
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentResponseDTO> update(@PathVariable Long id, @RequestBody @Valid StudentRequestDTO dto) {
		Student current = serviceStudent.updateStudent(id, dto);
		StudentResponseDTO response = mapperStudent.toResponseDTO(current);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceStudent.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}

}
