package com.edutech.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Coordinator;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

}
