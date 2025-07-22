package com.edutech.backend.dtos.disciplinaryrecord;

import com.edutech.backend.entities.DisciplinaryRecord;
import com.edutech.backend.enuns.TypeOccurrence;

import java.time.LocalDate;

public record DisciplinaryRecordResponseDTO(
        String studentName,
        LocalDate date,
        String responsibleName,
        TypeOccurrence occurrence) {

    public DisciplinaryRecordResponseDTO(DisciplinaryRecord entity){
        this(
                entity.getStudent() != null ? entity.getStudent().getName() : null,
                entity.getDate(),
                entity.getResponsible(),
                entity.getOccurrence()
        );
    }
}
