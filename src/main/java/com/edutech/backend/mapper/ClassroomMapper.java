package com.edutech.backend.mapper;

import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.entities.Classroom;

public class ClassroomMapper {

	public static Classroom toEntity(ClassroomRequestDTO dto) {
		return new Classroom(
				dto.series(),
				dto.identifierSeries(),
				dto.capacity(),
				dto.shift(),
				dto.schoolYear(),
				dto.modality());
	}

	public static void updateData(Classroom entity, ClassroomRequestDTO objUpdate) {
		entity.setSeries(objUpdate.series());
		entity.setIdentifierSeries(objUpdate.identifierSeries());
		entity.setCapacity(objUpdate.capacity());
		entity.setShift(objUpdate.shift());
		entity.setSchoolYear(objUpdate.schoolYear());
		entity.setModality(objUpdate.modality());
	}
}
