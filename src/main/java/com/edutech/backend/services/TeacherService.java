package com.edutech.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.backend.dtos.TeacherRequestDTO;
import com.edutech.backend.dtos.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.exceptions.ExistingResourceException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.TeacherMapper;
import com.edutech.backend.repositories.TeacherRepository;
import com.edutech.backend.utils.RegistrationGenerator;
import com.edutech.backend.utils.ValidatorUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {

	private final TeacherRepository repositoryTeacher;
	private final TeacherMapper mapperTeacher;
	private final CepService serviceCep;

	public List<TeacherResponseDTO> findAll() {
		return repositoryTeacher.findAll().stream().map(TeacherResponseDTO::new).toList();
	}

	public Teacher findById(Long id) {
		return repositoryTeacher.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Teacher createTeacher(TeacherRequestDTO dto) {
		Teacher newTeacher = mapperTeacher.toEntity(dto);
		prepareCreateTeacher(newTeacher, dto);
		return repositoryTeacher.save(newTeacher);
	}

	@Transactional
	public Teacher updateTeacher(Long id, TeacherRequestDTO obj) {
		try {
			Teacher entity = repositoryTeacher.getReferenceById(id);
			mapperTeacher.updateTeacherFromDTO(obj, entity);
			return repositoryTeacher.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteTeacher(Long id) {
		Teacher teacher = repositoryTeacher.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryTeacher.delete(teacher);
	}

	
	private void prepareCreateTeacher(Teacher teacher, TeacherRequestDTO dto) {
		ValidatorUtils.validateName(dto.name());
		ValidatorUtils.validateBirthDate(dto.dateBirth());
		ValidatorUtils.validateEmail(dto.email());
		ValidatorUtils.validateCpf(dto.cpf());
		ValidatorUtils.validateTelephone(dto.telephone());
		ValidatorUtils.validateCep(dto.cep());

		if (repositoryTeacher.findByEmail(dto.email()).isPresent()) {
			throw new ExistingResourceException("E-mail já cadastrado.");
		}

		teacher.setRegistration(new RegistrationGenerator()
				.generateRegistrationUniqueTeacher(teacher.getRegistration()));
		
		teacher.setAddress(serviceCep.findAdress(dto.cep()));
	}
}
