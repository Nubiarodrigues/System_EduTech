package com.edutech.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.CoordinatorRequestDTO;
import com.edutech.backend.dtos.CoordinatorResponseDTO;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.mapper.CoordinatorMapper;
import com.edutech.backend.repositories.CoordinatorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinatorService {

	private final CoordinatorRepository repositoryCoordinator;
	private final CoordinatorMapper mapperCoordinator;

	public List<CoordinatorResponseDTO> findAll() {
		return repositoryCoordinator.findAll().stream().map(CoordinatorResponseDTO::new).toList();
	}

	public Optional<Coordinator> findById(Long id) {
		Optional<Coordinator> coordinator = repositoryCoordinator.findById(id);
		return coordinator;
	}

	public Coordinator createCoordinator(CoordinatorRequestDTO dto) {
		Coordinator newCoordinator = mapperCoordinator.toEntity(dto);
		return repositoryCoordinator.save(newCoordinator);
	}

	public Coordinator updateCoordinator(Long id, CoordinatorRequestDTO dto) {
		Coordinator entity = repositoryCoordinator.getReferenceById(id);
		mapperCoordinator.updateStudentFromDTO(dto, entity);
		return repositoryCoordinator.save(entity);
	}

	public void deleteCoordinator(Long id) {
		Optional<Coordinator> coordinator = repositoryCoordinator.findById(id);
		repositoryCoordinator.deleteById(id);
	}

}
