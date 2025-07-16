package com.edutech.backend.dtos.bimestergrade;

import jakarta.validation.constraints.NotNull;

public record BimesterGradeRequestDTO(
        @NotNull Long idStudent,
        @NotNull Integer bimester,
        @NotNull Double grade1,
        @NotNull Double grade2,
        @NotNull Double grade3,
        @NotNull Double grade4) {
}
