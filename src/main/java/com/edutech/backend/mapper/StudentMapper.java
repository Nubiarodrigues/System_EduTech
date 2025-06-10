package com.edutech.backend.mapper;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.entities.Student;

public class StudentMapper {

	 public static Student toEntity(StudentRequestDTO dto) {
	        return new Student(
	            dto.name(),
	            dto.dateBirth(),
	            dto.cpf(),
	            dto.sus(),
	            dto.address(),
	            dto.telephone(),
	            dto.emailResponsable(),
	            dto.motherName(),
	            dto.fatherName()
	        );
	    }
	 
	 
	 public static void updateData(Student entity, StudentRequestDTO objUpdate) {
		 entity.setName(objUpdate.name());
		 entity.setDateBirth(objUpdate.dateBirth());
		 entity.setCpf(objUpdate.cpf());
		 entity.setSus(objUpdate.sus());
		 entity.setAddress(objUpdate.address());
		 entity.setTelephone(objUpdate.telephone());
		 entity.setEmailResponsable(objUpdate.emailResponsable());
		 entity.setMotherName(objUpdate.motherName());
		 entity.setFatherName(objUpdate.fatherName());
	 }
}
