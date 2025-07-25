package com.edutech.backend.dtos.discipline;

import com.edutech.backend.entities.Discipline;

public record DisciplineSimpleResponseDTO(String name, Integer workload) {
    public DisciplineSimpleResponseDTO(Discipline discipline) {
        this(discipline.getName(), discipline.getWorkload());
    }
}
