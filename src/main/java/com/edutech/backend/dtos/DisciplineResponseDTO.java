package com.edutech.backend.dtos;

import com.edutech.backend.entities.ClassesTaught;
import com.edutech.backend.entities.Discipline;

import java.util.List;

public record DisciplineResponseDTO(String name, Integer workload, String teacherName, List<ClassesTaught> classesTaught) {

    public DisciplineResponseDTO(Discipline discipline) {
        this(
                discipline.getName(),
                discipline.getWorkload(),
                discipline.getTeacher() != null ? discipline.getTeacher().getName() : null,
                discipline.getClassesTaught()
        );
    }
}
