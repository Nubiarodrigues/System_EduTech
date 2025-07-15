package com.edutech.backend.dtos.schoolnotice;

import jakarta.validation.constraints.NotNull;

public record SchoolNoticesRequestDTO(
        @NotNull String title,
        @NotNull String message) {
}
