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

import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.dtos.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.services.ClassroomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

	private final ClassroomService serviceClassroom;

	public ClassroomController(ClassroomService serviceClassroom) {
		this.serviceClassroom = serviceClassroom;
	}

	@GetMapping
	public ResponseEntity<List<ClassroomResponseDTO>> findAll() {
		List<ClassroomResponseDTO> classrooms = serviceClassroom.findAll();
		return ResponseEntity.ok(classrooms);
	}

	@PostMapping
	public ResponseEntity<ClassroomResponseDTO> create(@RequestBody @Valid ClassroomRequestDTO dto) {
		Classroom newClassroom = serviceClassroom.createClasseroom(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newClassroom.getId())
				.toUri();
		ClassroomResponseDTO response = new ClassroomResponseDTO(newClassroom);
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClassroomResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ClassroomRequestDTO obj) {
		Classroom current = serviceClassroom.updateClassroom(id, obj);
		ClassroomResponseDTO response = new ClassroomResponseDTO(current);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceClassroom.deleteClassroom(id);
		return ResponseEntity.noContent().build();
	}

}
