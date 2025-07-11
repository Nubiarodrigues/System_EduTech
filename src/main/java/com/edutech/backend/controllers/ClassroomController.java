package com.edutech.backend.controllers;

import com.edutech.backend.dtos.AddDisciplineClassroomRequestDTO;
import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.dtos.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.mapper.ClassroomMapper;
import com.edutech.backend.services.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

	private final ClassroomService serviceClassroom;
	private final ClassroomMapper mapperClassroom;

	@GetMapping
	public ResponseEntity<List<ClassroomResponseDTO>> findAll() {
		List<ClassroomResponseDTO> classrooms = serviceClassroom.findAll();
		return ResponseEntity.ok(classrooms);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassroomResponseDTO> findById(@PathVariable Long id) {
		Classroom classroom = serviceClassroom.findById(id);
		ClassroomResponseDTO response = mapperClassroom.toResponseDTO(classroom);
		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity<ClassroomResponseDTO> create(@RequestBody @Valid ClassroomRequestDTO dto) {
		Classroom newClassroom = serviceClassroom.createClassroom(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newClassroom.getId()).toUri();
		ClassroomResponseDTO response = mapperClassroom.toResponseDTO(newClassroom);
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClassroomResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ClassroomRequestDTO obj) {
		Classroom current = serviceClassroom.updateClassroom(id, obj);
		ClassroomResponseDTO response = mapperClassroom.toResponseDTO(current);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceClassroom.deleteClassroom(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{classroomId}/add-discipline")
	public ResponseEntity<Void> addDiscipline(@PathVariable Long classroomId, @RequestBody @Valid AddDisciplineClassroomRequestDTO disciplineId) {
		serviceClassroom.addDiscipline(classroomId, disciplineId);
		return ResponseEntity.ok().build();
	}

}
