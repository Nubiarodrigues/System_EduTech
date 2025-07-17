package com.edutech.backend.dtos.bimestergrade;

import jakarta.validation.constraints.NotNull;

public record BimesterGradeFinalRequestDTO(@NotNull Double gradeFinal) {
}
