package com.edutech.backend.dtos;

import com.edutech.backend.entities.ClassesTaught;
import com.edutech.backend.entities.Discipline;

import java.time.LocalDate;

public record ClassesTaughtResponseDTO(
        String description,
        LocalDate date,
        Discipline discipline) {

    public ClassesTaughtResponseDTO(ClassesTaught classesTaught){
        this(
                classesTaught.getDescription(),
                classesTaught.getDate(),
                classesTaught.getDiscipline()
        );
    }
}
