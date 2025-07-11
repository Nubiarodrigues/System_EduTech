package com.edutech.backend.dtos;

import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Teacher;

public record DisciplineResponseDTO(String name, int workload, Teacher teacher) {

    public DisciplineResponseDTO(Discipline discipline) {
        this (
                discipline.getName(),
                discipline.getWorkload(),
                discipline.getTeacher());
    }
}
