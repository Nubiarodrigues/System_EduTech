package com.edutech.backend.controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.entities.Student;
import com.edutech.backend.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentService serviceStudent;

	public StudentController(StudentService serviceStudent) {
		this.serviceStudent = serviceStudent;
	}

	@PostMapping
	public ResponseEntity<Student> insert(@RequestBody @Valid StudentRequestDTO dto) {
		Student newStudent = serviceStudent.createStudent(dto);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(newStudent.getId())
				.toUri();
		return ResponseEntity.created(location).body(newStudent);
	}

}
