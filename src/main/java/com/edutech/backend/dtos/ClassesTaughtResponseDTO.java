package com.edutech.backend.dtos;

import com.edutech.backend.entities.ClassesTaught;

import java.time.LocalDate;

public record ClassesTaughtResponseDTO(
        Long id,
        String description,
        LocalDate date,
        String disciplineName) {

    public ClassesTaughtResponseDTO(ClassesTaught classesTaught){
        this(
                classesTaught.getId(),
                classesTaught.getDescription(),
                classesTaught.getDate(),
                classesTaught.getDiscipline() != null ? classesTaught.getDiscipline().getName() : null
        );
    }
}
