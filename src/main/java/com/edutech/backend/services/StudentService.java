package com.edutech.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.dtos.StudentResponseDTO;
import com.edutech.backend.entities.Student;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.StudentMapper;
import com.edutech.backend.repositories.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

	private final StudentRepository repositoryStudent;

	public StudentService(StudentRepository repositoryStudent) {
		this.repositoryStudent = repositoryStudent;
	}

	public List<StudentResponseDTO> findAll() {
		return repositoryStudent.findAll().stream().map(StudentResponseDTO::new).toList();
	}

	public Student findById(Long id) {
		return repositoryStudent.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Student createStudent(StudentRequestDTO dto) {
		Student entity = StudentMapper.toEntity(dto);
		return repositoryStudent.save(entity);
	}

	@Transactional
	public Student updateStudent(Long id, StudentRequestDTO obj) {
		try {
			Student entity = repositoryStudent.getReferenceById(id);
			StudentMapper.updateData(entity, obj);
			return repositoryStudent.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteStudent(Long id) {
		Student student = repositoryStudent.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryStudent.delete(student);
	}

}
