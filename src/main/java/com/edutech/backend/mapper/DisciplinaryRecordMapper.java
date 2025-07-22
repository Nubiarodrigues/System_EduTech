package com.edutech.backend.mapper;

import com.edutech.backend.dtos.disciplinaryrecord.DisciplinaryRecordRequestDTO;
import com.edutech.backend.dtos.disciplinaryrecord.DisciplinaryRecordResponseDTO;
import com.edutech.backend.entities.DisciplinaryRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DisciplinaryRecordMapper {

    DisciplinaryRecord toEntity(DisciplinaryRecordRequestDTO dto);

    @Mapping(target = "studentName", expression = "java(entity.getStudent() != null ? entity.getStudent().getName() : null)")
    @Mapping(target = "responsibleName", source = "entity.responsible")
    DisciplinaryRecordResponseDTO toResponseDTO(DisciplinaryRecord entity);

    void updateDisciplinaryRecordFromDTO(DisciplinaryRecordRequestDTO dto, @MappingTarget DisciplinaryRecord entity);

}
