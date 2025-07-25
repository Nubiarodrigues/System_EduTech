package com.edutech.backend.controllers;

import com.edutech.backend.dtos.coordinator.CoordinatorRequestDTO;
import com.edutech.backend.dtos.coordinator.CoordinatorResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.mapper.CoordinatorMapper;
import com.edutech.backend.services.CoordinatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/coordinators")
@RequiredArgsConstructor
public class CoordinatorController {

	private final CoordinatorService serviceCoordinator;
	private final CoordinatorMapper mapperCoordinator;

	@GetMapping
	public ResponseEntity<List<CoordinatorResponseDTO>> findAll() {
		List<CoordinatorResponseDTO> coordinators = serviceCoordinator.findAll();
		return ResponseEntity.ok().body(coordinators);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CoordinatorResponseDTO> findById(@PathVariable Long id) {
		Coordinator coordinator = serviceCoordinator.findById(id);
		CoordinatorResponseDTO response = mapperCoordinator.toResponseDTO(coordinator);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{id}/classrooms")
	public ResponseEntity<List<Classroom>> getClassByCoordinator(@PathVariable Long id) {
		List<Classroom> classroom = serviceCoordinator.getClassroomByModality(id);
		return ResponseEntity.ok(classroom);
	}

	@PostMapping
	public ResponseEntity<CoordinatorResponseDTO> create(@RequestBody @Valid CoordinatorRequestDTO dto) {
		Coordinator newCoordinator = serviceCoordinator.create(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newCoordinator.getId())
				.toUri();
		CoordinatorResponseDTO response = mapperCoordinator.toResponseDTO(newCoordinator);
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CoordinatorResponseDTO> update(@PathVariable Long id, @RequestBody @Valid CoordinatorRequestDTO dto) {
		Coordinator current = serviceCoordinator.update(id, dto);
		CoordinatorResponseDTO response = mapperCoordinator.toResponseDTO(current);
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceCoordinator.delete(id);
		return ResponseEntity.noContent().build();
	}
}