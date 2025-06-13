package com.edutech.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.enuns.Situation;
import com.edutech.backend.enuns.TeachingState;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

	Optional<Coordinator> findByModalityAndStatus(TeachingState modality, Situation status);
}
