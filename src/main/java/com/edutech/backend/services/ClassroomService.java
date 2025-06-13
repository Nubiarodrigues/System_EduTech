package com.edutech.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.dtos.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.enuns.Situation;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.ClassroomMapper;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.CoordinatorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomService {

	private final ClassroomRepository repositoryClassroom;
	private final ClassroomMapper mapperClassroom;
	private final CoordinatorRepository repositoryCoordinator;

	public List<ClassroomResponseDTO> findAll() {
		return repositoryClassroom.findAll().stream().map(ClassroomResponseDTO::new).toList();
	}

	public Classroom findById(Long id) {
		return repositoryClassroom.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Classroom createClassroom(ClassroomRequestDTO dto) {
		Coordinator coordinator = repositoryCoordinator
				.findByModalityAndStatus(dto.modality(), Situation.ATIVO)
				.orElseThrow(() -> new ResourceNotFoundException("Coordenador não encontrado para esta modalidade"));

		Classroom classroom = mapperClassroom.toEntity(dto);
		classroom.setCoordinatorClass(coordinator);
		classroom = repositoryClassroom.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom updateClassroom(Long id, ClassroomRequestDTO obj) {
		try {
			Classroom entity = repositoryClassroom.getReferenceById(id);
			mapperClassroom.updateClassroomFromDTO(obj, entity);
			return repositoryClassroom.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteClassroom(Long id) {
		Classroom classroom = repositoryClassroom.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryClassroom.delete(classroom);
	}

}
