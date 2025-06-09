package com.edutech.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>  {

}
