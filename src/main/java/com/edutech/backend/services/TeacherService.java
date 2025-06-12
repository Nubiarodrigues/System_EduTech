package com.edutech.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.TeacherRequestDTO;
import com.edutech.backend.dtos.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.TeacherMapper;
import com.edutech.backend.repositories.TeacherRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeacherService {

	private final TeacherRepository repositoryTeacher;
	private final TeacherMapper mapperTeacher;

	public TeacherService(TeacherRepository repositoryTeacher, TeacherMapper mapperTeacher) {
		this.repositoryTeacher = repositoryTeacher;
		this.mapperTeacher = mapperTeacher;
	}

	public List<TeacherResponseDTO> findAll() {
		return repositoryTeacher.findAll().stream().map(TeacherResponseDTO::new).toList();
	}

	public Teacher findById(Long id) {
		return repositoryTeacher.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Teacher createTeacher(TeacherRequestDTO dto) {
		Teacher newTeacher = mapperTeacher.toEntity(dto);
		return repositoryTeacher.save(newTeacher);
	}

	public Teacher updateTeacher(Long id, TeacherRequestDTO obj) {
		try {
			Teacher entity = repositoryTeacher.getReferenceById(id);
			mapperTeacher.updateTeacherFromDTO(obj, entity);
			return repositoryTeacher.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void deleteTeacher(Long id) {
		Teacher teacher = repositoryTeacher.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryTeacher.delete(teacher);
	}
}
