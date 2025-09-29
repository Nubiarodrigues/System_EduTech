package com.edutech.backend.dtos.school;

import com.edutech.backend.entities.embeddable.Address;
import com.edutech.backend.enuns.TeachingStage;
import com.edutech.backend.enuns.TypeSchool;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record SchoolRequestDTO(
        @NotNull String name,
        @NotNull String email,
        @NotNull String telephone,
        @NotNull String cnpj,
        @NotNull Integer capacity,
        @NotNull Address address,
        @NotNull Set<TeachingStage> stages,
        @NotNull TypeSchool typeSchool) {
}
