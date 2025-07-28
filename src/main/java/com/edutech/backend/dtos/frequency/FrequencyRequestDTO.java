package com.edutech.backend.dtos.frequency;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record FrequencyRequestDTO(
        @NotNull LocalDate dateRegistration,
        @NotNull List<Integer> presenceDay) {
}
