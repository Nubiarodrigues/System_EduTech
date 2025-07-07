package com.edutech.backend.dtos;

import com.edutech.backend.entities.Discipline;

public record DisciplineResponseDTO(String name, int workload, String nameTeacher) {

    public DisciplineResponseDTO(Discipline discipline) {
        this (
                discipline.getName(),
                discipline.getWorkload(),
                discipline.getTeacher() != null ? discipline.getTeacher().getName() : null);
    }
}
