package com.edutech.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.dtos.StudentResponseDTO;
import com.edutech.backend.entities.Student;
import com.edutech.backend.mapper.StudentMapper;
import com.edutech.backend.repositories.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository repositoryStudent;

	public StudentService(StudentRepository repositoryStudent) {
		this.repositoryStudent = repositoryStudent;
	}

	public List<StudentResponseDTO> findAll() {
		return repositoryStudent.findAll().stream().map(StudentResponseDTO::new).toList();
	}

	public Optional<Student> findById(Long id) {
		return repositoryStudent.findById(id);
	}

	public Student createStudent(StudentRequestDTO dto) {
		Student entity = StudentMapper.toEntity(dto);
		return repositoryStudent.save(entity);
	}

}
