package com.edutech.backend.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.dtos.StudentResponseDTO;
import com.edutech.backend.entities.Student;
import com.edutech.backend.exceptions.DatabaseException;
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

	public Student createStudent(StudentRequestDTO dto) {
		Student entity = StudentMapper.toEntity(dto);
		return repositoryStudent.save(entity);
	}

	public Student updateStudent(Long id, StudentRequestDTO obj) {
		try {
			Student entity = repositoryStudent.getReferenceById(id);
			StudentMapper.updateData(entity, obj);
			return repositoryStudent.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void deleteStudent(Long id) {
		try {
			repositoryStudent.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

}
