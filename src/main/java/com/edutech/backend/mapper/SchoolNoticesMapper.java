package com.edutech.backend.mapper;

import com.edutech.backend.dtos.SchoolNoticesRequestDTO;
import com.edutech.backend.dtos.SchoolNoticesResponseDTO;
import com.edutech.backend.entities.SchoolNotices;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SchoolNoticesMapper {

    SchoolNotices toEntity(SchoolNoticesRequestDTO schoolNoticesRequestDTO);

    SchoolNoticesResponseDTO toResponseDTO(SchoolNotices schoolNotices);

    void updateSchoolNoticesFromDTO(SchoolNoticesRequestDTO dto, @MappingTarget SchoolNotices schoolNotices);
}
