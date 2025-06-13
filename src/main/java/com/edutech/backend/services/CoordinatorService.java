package com.edutech.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.CoordinatorResponseDTO;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.repositories.CoordinatorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinatorService {

	private final CoordinatorRepository repositoryCoordinator;

	public List<CoordinatorResponseDTO> findAll() {
		return repositoryCoordinator.findAll().stream().map(CoordinatorResponseDTO::new).toList();
	}

	public Optional<Coordinator> findById(Long id) {
		Optional<Coordinator> coordinator = repositoryCoordinator.findById(id);
		return coordinator;
	}

}
