package com.edutech.backend.mapper;

import com.edutech.backend.dtos.bimestergrade.BimesterGradeRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeResponseDTO;
import com.edutech.backend.entities.BimesterGrade;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BimesterGradeMapper {

    BimesterGrade toEntity(BimesterGradeRequestDTO dto);

    BimesterGradeResponseDTO toResponseDTO(BimesterGrade entity);

    void updateBimesterFromDTO(BimesterGradeRequestDTO dto, @MappingTarget BimesterGrade entity);

}
