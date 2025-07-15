package com.edutech.backend.dtos.discipline;

import com.edutech.backend.dtos.classestaught.ClassesTaughtResponseDTO;
import com.edutech.backend.entities.Discipline;

import java.util.List;
import java.util.stream.Collectors;

public record DisciplineResponseDTO(
        String name,
        Integer workload,
        String teacherName,
        List<ClassesTaughtResponseDTO> classesTaught) {

    public DisciplineResponseDTO(Discipline discipline) {
        this(
                discipline.getName(),
                discipline.getWorkload(),
                discipline.getTeacher() != null ? discipline.getTeacher().getName() : null,
                discipline.getClassesTaught() != null ? discipline.getClassesTaught().stream().map(ClassesTaughtResponseDTO::new).collect(Collectors.toList()) : null
        );
    }
}
