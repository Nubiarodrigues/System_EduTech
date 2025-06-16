package com.edutech.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.backend.dtos.CoordinatorRequestDTO;
import com.edutech.backend.dtos.CoordinatorResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.CoordinatorMapper;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.CoordinatorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinatorService {

	private final CoordinatorRepository repositoryCoordinator;
	private final CoordinatorMapper mapperCoordinator;
	private final ClassroomRepository repositoryClassroom;

	public List<CoordinatorResponseDTO> findAll() {
		return repositoryCoordinator.findAll().stream().map(CoordinatorResponseDTO::new).toList();
	}

	public Coordinator findById(Long id) {
		return repositoryCoordinator.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Coordinator createCoordinator(CoordinatorRequestDTO dto) {
		Coordinator newCoordinator = mapperCoordinator.toEntity(dto);
		return repositoryCoordinator.save(newCoordinator);
	}

	@Transactional
	public Coordinator updateCoordinator(Long id, CoordinatorRequestDTO dto) {
		try {
			Coordinator entity = repositoryCoordinator.getReferenceById(id);
			mapperCoordinator.updateCoordinatorFromDTO(dto, entity);
			return repositoryCoordinator.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteCoordinator(Long id) {
		Coordinator coordinator = repositoryCoordinator.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryCoordinator.deleteById(id);
	}

	public List<Classroom> getClassroomByModality(Long id) {
		Coordinator coordinator = repositoryCoordinator.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return repositoryClassroom.findByModality(coordinator.getModality());
	}

}
