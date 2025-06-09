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
	 
	 public static void updateData(Student entity, Student objUpdate) {
		 entity.setName(objUpdate.getName());
		 entity.setDateBirth(objUpdate.getDateBirth());
		 entity.setCpf(objUpdate.getCpf());
		 entity.setSus(objUpdate.getSus());
		 entity.setAddress(objUpdate.getAddress());
		 entity.setTelephone(objUpdate.getTelephone());
		 entity.setEmailResponsable(objUpdate.getEmailResponsable());
		 entity.setMotherName(objUpdate.getMotherName());
		 entity.setFatherName(objUpdate.getFatherName());
	 }
}
