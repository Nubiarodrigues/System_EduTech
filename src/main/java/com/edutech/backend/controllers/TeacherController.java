package com.edutech.backend.controllers;

import com.edutech.backend.dtos.teacher.TeacherRequestDTO;
import com.edutech.backend.dtos.teacher.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.mapper.TeacherMapper;
import com.edutech.backend.services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

	private final TeacherService serviceTeacher;
	private final TeacherMapper mapperTeacher;

	@GetMapping
	public ResponseEntity<List<TeacherResponseDTO>> findAll() {
		List<TeacherResponseDTO> teachers = serviceTeacher.findAll();
		return ResponseEntity.ok(teachers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeacherResponseDTO> findById(@PathVariable Long id) {
		Teacher teacher = serviceTeacher.findById(id);
		TeacherResponseDTO response = mapperTeacher.toResponseDTO(teacher);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity<TeacherResponseDTO> create(@RequestBody @Valid TeacherRequestDTO dto) {
		Teacher newTeacher = serviceTeacher.create(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newTeacher.getId())
				.toUri();
		TeacherResponseDTO response = mapperTeacher.toResponseDTO(newTeacher);
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TeacherResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TeacherRequestDTO dto) {
		Teacher current = serviceTeacher.update(id, dto);
		TeacherResponseDTO response = mapperTeacher.toResponseDTO(current);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceTeacher.delete(id);
		return ResponseEntity.noContent().build();
	}
}