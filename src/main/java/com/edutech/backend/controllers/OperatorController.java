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

import com.edutech.backend.dtos.operator.OperatorRequestDTO;
import com.edutech.backend.dtos.operator.OperatorResponseDTO;
import com.edutech.backend.entities.Operator;
import com.edutech.backend.mapper.OperatorMapper;
import com.edutech.backend.services.OperatorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("operators")
@RequiredArgsConstructor
public class OperatorController {

	private final OperatorService serviceOperator;
	private final OperatorMapper mapperOperator;

	@GetMapping
	public ResponseEntity<List<OperatorResponseDTO>> findAll() {
		List<OperatorResponseDTO> operators = serviceOperator.findAll();
		return ResponseEntity.ok().body(operators);
	}

	@PostMapping
	public ResponseEntity<OperatorResponseDTO> create(@RequestBody @Valid OperatorRequestDTO dto) {
		Operator newOperator = serviceOperator.createOperator(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newOperator.getId())
				.toUri();
		OperatorResponseDTO response = mapperOperator.toResponseDTO(newOperator);
		return ResponseEntity.created(location).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OperatorResponseDTO> update(@PathVariable Long id, @RequestBody OperatorRequestDTO dto) {
		Operator current = serviceOperator.updateOperator(id, dto);
		OperatorResponseDTO response = mapperOperator.toResponseDTO(current);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		serviceOperator.deleteOperator(id);
		return ResponseEntity.noContent().build();
	}

}
