package com.edutech.backend.mapper;

import com.edutech.backend.dtos.frequency.FrequencyRequestDTO;
import com.edutech.backend.dtos.frequency.FrequencyResponseDTO;
import com.edutech.backend.entities.Frequency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collections.class, Collectors.class})
public interface FrequencyMapper {

    @Mapping(target = "presenceDay", source = "dto.presenceDay")
    Frequency toEntity(FrequencyRequestDTO dto);

    @Mapping(target = "percentageDay", source = "entity.percentageDay")
    @Mapping(target = "nameDiscipline", source = "entity.discipline.name")
    FrequencyResponseDTO toResponse(Frequency entity);

    void updateFrequencyFromDTO(FrequencyRequestDTO dto, @MappingTarget Frequency frequency);
}
