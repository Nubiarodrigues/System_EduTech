package com.edutech.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.dtos.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.mapper.ClassroomMapper;
import com.edutech.backend.repositories.ClassroomRepository;

@Service
public class ClassroomService {

	private final ClassroomRepository repositoryClassroom;

	public ClassroomService(ClassroomRepository repositoryClassroom) {
		this.repositoryClassroom = repositoryClassroom;
	}

	public List<ClassroomResponseDTO> findAll() {
		return repositoryClassroom.findAll().stream().map(ClassroomResponseDTO::new).toList();
	}

	public Optional<Classroom> findById(Long id) {
		Optional<Classroom> classroom = repositoryClassroom.findById(id);
		return classroom;
	}

	public Classroom createClasseroom(ClassroomRequestDTO dto) {
		Classroom entity = ClassroomMapper.toEntity(dto);
		return repositoryClassroom.save(entity);
	}

	public Classroom updateClassroom(Long id, ClassroomRequestDTO obj) {
		Classroom entity = repositoryClassroom.getReferenceById(id);
		ClassroomMapper.updateData(entity, obj);
		return repositoryClassroom.save(entity);
	}

	public void deleteClassroom(Long id) {
		Optional<Classroom> entity = repositoryClassroom.findById(id);
	}

}
