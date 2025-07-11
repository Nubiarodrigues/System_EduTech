package com.edutech.backend.dtos;

import jakarta.validation.constraints.NotNull;

public record DisciplineRequestDTO(@NotNull String name, @NotNull int workload, @NotNull Long teacherId) {
}
