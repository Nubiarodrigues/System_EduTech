package com.edutech.backend.repositories;

import java.util.List;

import com.edutech.backend.enuns.TeachingStage;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Classroom;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>, JpaSpecificationExecutor<Classroom> {

	List<Classroom> findByModality(TeachingStage modality);
}
