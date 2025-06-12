package com.edutech.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.TeacherRequestDTO;
import com.edutech.backend.dtos.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.mapper.TeacherMapper;
import com.edutech.backend.repositories.TeacherRepository;

@Service
public class TeacherService {

	private final TeacherRepository repositoryTeacher;

	public TeacherService(TeacherRepository repositoryTeacher) {
		this.repositoryTeacher = repositoryTeacher;
	}

	public List<TeacherResponseDTO> findAll() {
		return repositoryTeacher.findAll().stream().map(TeacherResponseDTO::new).toList();
	}

	public Optional<Teacher> findById(Long id) {
		return repositoryTeacher.findById(id);
	}

	public Teacher createTeacher(TeacherRequestDTO teacher) {
		Teacher newTeacher = TeacherMapper.toEntity(teacher);
		return repositoryTeacher.save(newTeacher);
	}

	public Teacher updateTeacher(Long id, TeacherRequestDTO obj) {
		Teacher entity = repositoryTeacher.getReferenceById(id);
		TeacherMapper.updateData(entity, obj);
		return repositoryTeacher.save(entity);
	}
	
	public void deleteTeacher(Long id) {
		repositoryTeacher.deleteById(id);
	}
}
