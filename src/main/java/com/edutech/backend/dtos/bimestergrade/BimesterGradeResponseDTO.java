package com.edutech.backend.dtos.bimestergrade;

import com.edutech.backend.entities.BimesterGrade;
import com.edutech.backend.enuns.SituationStudent;

public record BimesterGradeResponseDTO(
        String nameStudent,
        String nameDiscipline,
        SituationStudent situation,
        Integer bimester,
        Double finalAverage) {

    public BimesterGradeResponseDTO(BimesterGrade bimesterGrade) {
        this(
                bimesterGrade.getStudent() != null ? bimesterGrade.getStudent().getName() : null,
                bimesterGrade.getDiscipline() != null ? bimesterGrade.getDiscipline().getName() : null,
                bimesterGrade.getSituation(),
                bimesterGrade.getBimester(),
                bimesterGrade.finalAverageCalculation()
        );
    }
}

