package com.edutech.backend.dtos.bimestergrade;

import com.edutech.backend.entities.BimesterGrade;
import com.edutech.backend.enuns.SituationStudent;

public record BimesterGradeFinalResponseDTO(
        String nameStudent,
        String nameDiscipline,
        SituationStudent situation,
        Integer bimester,
        Double averageGlobal
) {
    public BimesterGradeFinalResponseDTO(BimesterGrade bimesterGrade){
        this(
                bimesterGrade.getStudent() != null ? bimesterGrade.getStudent().getName() : null,
                bimesterGrade.getDiscipline() != null ? bimesterGrade.getDiscipline().getName() : null,
                bimesterGrade.getSituation(),
                bimesterGrade.getBimester(),
                bimesterGrade.finalGlobalCalculation()
        );
    }
}
