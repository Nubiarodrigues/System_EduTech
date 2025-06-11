package com.edutech.backend.controllers;

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

import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.dtos.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.services.ClassroomService;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

	private final ClassroomService serviceClassroom;

	public ClassroomController(ClassroomService serviceClassroom) {
		this.serviceClassroom = serviceClassroom;
	}

	@GetMapping
	public ResponseEntity<List<ClassroomResponseDTO>> findAll() {
		List<ClassroomResponseDTO> classroom = serviceClassroom.findAll();
		return ResponseEntity.ok().body(classroom);
	}

	@PostMapping
	public ResponseEntity<ClassroomResponseDTO> insert(@RequestBody ClassroomRequestDTO dto) {
		Classroom newClassroom = serviceClassroom.createClasseroom(dto);
		ClassroomResponseDTO response = new ClassroomResponseDTO(newClassroom);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClassroomResponseDTO> update(@PathVariable Long id, @RequestBody ClassroomRequestDTO obj) {
		Classroom current = serviceClassroom.updateClassroom(id, obj);
		ClassroomResponseDTO response = new ClassroomResponseDTO(current);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceClassroom.deleteClassroom(id);
		return ResponseEntity.ok().build();
	}

}
