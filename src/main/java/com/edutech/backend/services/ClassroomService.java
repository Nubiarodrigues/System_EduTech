package com.edutech.backend.services;

import com.edutech.backend.dtos.discipline.AddDisciplineClassroomRequestDTO;
import com.edutech.backend.dtos.classroom.ClassroomRequestDTO;
import com.edutech.backend.dtos.classroom.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.enuns.Situation;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.ClassroomMapper;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.CoordinatorRepository;
import com.edutech.backend.repositories.DisciplineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomService {

	private final ClassroomRepository repositoryClassroom;
	private final ClassroomMapper mapperClassroom;
	private final CoordinatorRepository repositoryCoordinator;
	private final DisciplineRepository repositoryDiscipline;

	public List<ClassroomResponseDTO> findAll() {
		return repositoryClassroom.findAll().stream().map(ClassroomResponseDTO::new).toList();
	}

	public Classroom findById(Long id) {
		return repositoryClassroom.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Classroom createClassroom(ClassroomRequestDTO dto) {
		Coordinator coordinator = repositoryCoordinator.findByModalityAndStatus(dto.modality(), Situation.ATIVO)
				.orElseThrow(() -> new ResourceNotFoundException("Coordenador não encontrado para esta modalidade"));

		Classroom classroom = mapperClassroom.toEntity(dto);
		classroom.setCoordinatorClass(coordinator);
		classroom = repositoryClassroom.save(classroom);
		return classroom;
	}

	@Transactional
	public Classroom updateClassroom(Long id, ClassroomRequestDTO dto) {
		try {
			Classroom entity = repositoryClassroom.getReferenceById(id);
			mapperClassroom.updateClassroomFromDTO(dto, entity);
			Coordinator coordinator = repositoryCoordinator.findByModalityAndStatus(dto.modality(), Situation.ATIVO)
					.orElseThrow(
							() -> new ResourceNotFoundException("Coordenador não encontrado para esta modalidade"));

			entity.setCoordinatorClass(coordinator);
			return repositoryClassroom.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteClassroom(Long id) {
		Classroom classroom = repositoryClassroom.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryClassroom.delete(classroom);
	}

	@Transactional
	public void addDiscipline (Long classroomId, AddDisciplineClassroomRequestDTO dto) {

		Discipline discipline = repositoryDiscipline.findById(dto.disciplineId())
				.orElseThrow(() -> new ResourceNotFoundException("Disciplina com ID " +  dto.disciplineId() + " não encontrada."));


		Classroom classroom = repositoryClassroom.findById(classroomId)
				.orElseThrow(() -> new ResourceNotFoundException("Turma com ID " + classroomId + " não encontrada."));

		classroom.getDisciplines().add(discipline);

		repositoryClassroom.save(classroom);
	}

}
