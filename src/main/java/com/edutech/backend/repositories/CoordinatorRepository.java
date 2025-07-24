package com.edutech.backend.repositories;

import java.util.Optional;

import com.edutech.backend.enuns.TeachingStage;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.enuns.Situation;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

	Optional<Coordinator> findByModalityAndStatus(TeachingStage modality, Situation status);
	
	Optional<Coordinator> findByEmail(String email);
}
