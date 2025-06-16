package com.edutech.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

}
