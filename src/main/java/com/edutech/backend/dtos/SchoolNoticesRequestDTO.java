package com.edutech.backend.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SchoolNoticesRequestDTO(
        @NotNull String title,
        @NotNull String message,
        @NotNull LocalDate dateCreation,
        @NotNull String author) {
}
