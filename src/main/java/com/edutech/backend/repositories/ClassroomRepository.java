package com.edutech.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
