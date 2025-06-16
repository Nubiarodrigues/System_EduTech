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

import com.edutech.backend.dtos.AdministratorRequestDTO;
import com.edutech.backend.dtos.AdministratorResponseDTO;
import com.edutech.backend.entities.Administrator;
import com.edutech.backend.mapper.AdministratorMapper;
import com.edutech.backend.services.AdministratorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/administrators")
@RequiredArgsConstructor
public class AdministratorController {

	private final AdministratorService serviceAdministrator;
	private final AdministratorMapper mapperAdministrator;

	@GetMapping
	public ResponseEntity<List<AdministratorResponseDTO>> findAll() {
		List<AdministratorResponseDTO> administrators = serviceAdministrator.findAll();
		return ResponseEntity.ok(administrators);
	}

	@PostMapping
	public ResponseEntity<AdministratorResponseDTO> create(@RequestBody @Valid AdministratorRequestDTO dto) {
		Administrator newAdministrator = serviceAdministrator.createAdmin(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newAdministrator.getId())
				.toUri();
		AdministratorResponseDTO response = mapperAdministrator.toResponseDTO(newAdministrator);
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AdministratorResponseDTO> update(@PathVariable Long id,
			@RequestBody @Valid AdministratorRequestDTO dto) {
		Administrator current = serviceAdministrator.updateAdmin(id, dto);
		AdministratorResponseDTO response = mapperAdministrator.toResponseDTO(current);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceAdministrator.deleteAdmin(id);
		return ResponseEntity.noContent().build();
	}
}
