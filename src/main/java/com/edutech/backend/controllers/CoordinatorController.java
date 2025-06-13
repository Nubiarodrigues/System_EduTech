package com.edutech.backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.backend.dtos.CoordinatorRequestDTO;
import com.edutech.backend.dtos.CoordinatorResponseDTO;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.mapper.CoordinatorMapper;
import com.edutech.backend.services.CoordinatorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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

	@PostMapping
	public ResponseEntity<Coordinator> create(@RequestBody @Valid CoordinatorRequestDTO dto) {
		Coordinator newCoordinator = serviceCoordinator.createCoordinator(dto);
		CoordinatorResponseDTO response = mapperCoordinator.toResponseDTO(newCoordinator);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCoordinator);
	}

}
