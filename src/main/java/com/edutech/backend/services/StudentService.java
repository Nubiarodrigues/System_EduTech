package com.edutech.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.StudentResponseDTO;
import com.edutech.backend.entities.Student;
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

	public Student createStudent() {
		Student newStudent = new Student();
		return repositoryStudent.save(newStudent);
	}

}
