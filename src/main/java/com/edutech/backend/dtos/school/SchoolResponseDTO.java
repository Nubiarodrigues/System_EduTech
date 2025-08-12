package com.edutech.backend.dtos.school;

import com.edutech.backend.entities.School;
import com.edutech.backend.entities.embeddable.Address;
import com.edutech.backend.enuns.TeachingStage;
import com.edutech.backend.enuns.TypeSchool;

import java.util.Set;

public record SchoolResponseDTO(
        String name,
        String email,
        String telephone,
        String cnpj,
        Address address,
        Set<TeachingStage> stages,
        TypeSchool typeSchool) {

    public SchoolResponseDTO(School school){
        this(
                school.getName(),
                school.getEmail(),
                school.getTelephone(),
                school.getCnpj(),
                school.getAddress(),
                school.getStages(),
                school.getTypeSchool()
        );
    }
}
