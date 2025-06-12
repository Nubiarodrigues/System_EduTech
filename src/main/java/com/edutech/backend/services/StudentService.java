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
	private final StudentMapper mapperStudent;

	public StudentService(StudentRepository repositoryStudent, StudentMapper mapperStudent) {
		this.repositoryStudent = repositoryStudent;
		this.mapperStudent = mapperStudent;
	}

	public List<StudentResponseDTO> findAll() {
		return repositoryStudent.findAll().stream().map(StudentResponseDTO::new).toList();
	}

	public Student findById(Long id) {
		return repositoryStudent.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Student createStudent(StudentRequestDTO dto) {
		Student entity = mapperStudent.toEntity(dto);
		return repositoryStudent.save(entity);
	}

	@Transactional
	public Student updateStudent(Long id, StudentRequestDTO obj) {
		try {
			Student entity = repositoryStudent.getReferenceById(id);
			mapperStudent.updateStudentFromDTO(obj, entity);
			return repositoryStudent.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteStudent(Long id) {
		Student student = repositoryStudent.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryStudent.delete(student);
	}

}
