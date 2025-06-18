package com.edutech.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.backend.entities.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

	Optional<Administrator> findByEmail(String email);

}
