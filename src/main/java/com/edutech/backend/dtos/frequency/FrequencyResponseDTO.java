package com.edutech.backend.dtos.frequency;

import com.edutech.backend.entities.Frequency;

import java.time.LocalDate;

public record FrequencyResponseDTO(
        LocalDate dateRegistration,
        String nameDiscipline,
        Double percentageDay) {

    public FrequencyResponseDTO(Frequency frequency) {
        this(
                frequency.getDateRegistration(),
                frequency.getDiscipline().getName(),
                frequency.getPercentageDay());
    }
}
