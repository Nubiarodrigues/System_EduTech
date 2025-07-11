package com.edutech.backend.dtos;

import jakarta.validation.constraints.NotNull;

public record AddDisciplineClassroomRequestDTO(@NotNull Long disciplineId) {
}
