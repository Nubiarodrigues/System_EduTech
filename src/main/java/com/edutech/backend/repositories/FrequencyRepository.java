package com.edutech.backend.repositories;

import com.edutech.backend.entities.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrequencyRepository extends JpaRepository<Frequency, Long> {

    List<Frequency> findByStudentId(Long id);

}
