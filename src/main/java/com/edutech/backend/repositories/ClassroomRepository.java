package com.edutech.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.enuns.TeachingState;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

	List<Classroom> findByModality(TeachingState modality);
}
