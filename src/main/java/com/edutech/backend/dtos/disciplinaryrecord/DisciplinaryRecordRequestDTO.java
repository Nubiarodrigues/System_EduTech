package com.edutech.backend.dtos.disciplinaryrecord;

import com.edutech.backend.enuns.TypeOccurrence;
import jakarta.validation.constraints.NotNull;

public record DisciplinaryRecordRequestDTO(
        @NotNull String description,
        @NotNull String measures,
        @NotNull Long idStudent,
        @NotNull TypeOccurrence occurrence) {
}
