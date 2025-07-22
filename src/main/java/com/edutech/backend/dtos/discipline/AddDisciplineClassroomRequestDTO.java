package com.edutech.backend.dtos.discipline;

import jakarta.validation.constraints.NotNull;

public record AddDisciplineClassroomRequestDTO(@NotNull Long disciplineId) {
}
