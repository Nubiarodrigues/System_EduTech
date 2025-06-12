package com.edutech.backend.mapper;

import com.edutech.backend.dtos.TeacherRequestDTO;
import com.edutech.backend.entities.Teacher;

public class TeacherMapper {

	public static Teacher toEntity(TeacherRequestDTO dto) {
		return new Teacher(
				dto.name(),
				dto.cpf(),
				dto.telephone(),
				dto.status(),
				dto.dateBirth(),
				dto.workloadTotal(),
				dto.rg(),
				dto.address(),
				dto.matriculation(),
				dto.formedCourse());	
	}
	
	public static void updateData(Teacher entity, TeacherRequestDTO dto) {
		entity.setName(dto.name());
		entity.setCpf(dto.cpf());
		entity.setTelephone(dto.telephone());
		entity.setStatus(dto.status());
		entity.setDateBirth(dto.dateBirth());
		entity.setWorkloadTotal(dto.workloadTotal());
		entity.setAddress(dto.address());
		entity.setMatriculation(dto.matriculation());
		entity.setFormedCourse(dto.formedCourse());
	}

}
