package com.edutech.backend.dtos.classestaught;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClassesTaughtRequestDTO(@NotNull String description, @NotNull LocalDate date) {
}
