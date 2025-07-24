package com.edutech.backend.repositories;

import java.util.List;

import com.edutech.backend.enuns.TeachingStage;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

	List<Classroom> findByModality(TeachingStage modality);
}
