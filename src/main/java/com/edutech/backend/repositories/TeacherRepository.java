package com.edutech.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	Optional<Teacher> findByEmail(String email);

}
