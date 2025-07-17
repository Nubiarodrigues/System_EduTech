package com.edutech.backend.mapper;

import com.edutech.backend.dtos.bimestergrade.BimesterGradeFinalRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeResponseDTO;
import com.edutech.backend.entities.BimesterGrade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BimesterGradeMapper {

    BimesterGrade toEntity(BimesterGradeRequestDTO dto);

    @Mapping(target = "nameStudent", source = "student.name")
    @Mapping(target = "nameDiscipline", source = "discipline.name")
    @Mapping(target = "situation", source = "situation")
    @Mapping(target = "bimester", source = "bimester")
    @Mapping(target = "averageFinal", expression = "java(entity.getGradeFinal() != null ? entity.finalGlobalCalculation() : entity.finalAverageCalculation())")
    BimesterGradeResponseDTO toResponseDTO(BimesterGrade entity);

    @Mapping(target = "bimester", source = "bimester")
    @Mapping(target = "grade1", source = "grade1")
    @Mapping(target = "grade2", source = "grade2")
    @Mapping(target = "grade3", source = "grade3")
    @Mapping(target = "grade4", source = "grade4")
    void updateBimesterFromDTO(BimesterGradeRequestDTO dto, @MappingTarget BimesterGrade entity);

    @Mapping(target = "gradeFinal", source = "gradeFinal")
    void updateBimesterFinalFromDTO(BimesterGradeFinalRequestDTO dto, @MappingTarget BimesterGrade entity);
}
