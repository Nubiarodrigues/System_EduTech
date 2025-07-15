package com.edutech.backend.dtos.discipline;

import jakarta.validation.constraints.NotNull;

public record DisciplineRequestDTO(@NotNull String name, @NotNull int workload) {
}
