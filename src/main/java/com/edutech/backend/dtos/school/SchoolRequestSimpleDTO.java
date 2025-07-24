package com.edutech.backend.dtos.school;

import com.edutech.backend.enuns.TeachingStage;

import java.util.Set;

public record SchoolRequestSimpleDTO(
        String email,
        String telephone,
        Set<TeachingStage> stages) {
}
