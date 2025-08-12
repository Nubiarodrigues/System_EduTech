package com.edutech.backend.services;

import com.edutech.backend.dtos.teacher.TeacherRequestDTO;
import com.edutech.backend.dtos.teacher.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.exceptions.ExistingResourceException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.TeacherMapper;
import com.edutech.backend.repositories.TeacherRepository;
import com.edutech.backend.utils.RegistrationGenerator;
import com.edutech.backend.utils.UserLoggedUtils;
import com.edutech.backend.utils.ValidatorUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

	private final TeacherRepository repositoryTeacher;
	private final TeacherMapper mapperTeacher;
	private final CepService serviceCep;
	private final UserLoggedUtils  userLoggedUtils;

	public List<TeacherResponseDTO> findAll() {
		return repositoryTeacher.findAll().stream().map(mapperTeacher::toResponseDTO).toList();
	}

	public Teacher findById(Long id) {
		return repositoryTeacher.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Professor com ID: " + id + " não existe."));
	}

	@Transactional
	public Teacher create(TeacherRequestDTO dto) {
		Teacher newTeacher = mapperTeacher.toEntity(dto);
		prepareCreateTeacher(newTeacher, dto);
		return repositoryTeacher.save(newTeacher);
	}

	@Transactional
	public Teacher update(Long id, TeacherRequestDTO obj) {
		try {
			Teacher entity = repositoryTeacher.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Professor com ID: " + id + " não existe."));

			mapperTeacher.updateTeacherFromDTO(obj, entity);
			return repositoryTeacher.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void delete(Long id) {
		Teacher teacher = repositoryTeacher.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Professor com ID: " + id + " não existe."));
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

		if(teacher.getWorkloadAllocated() == null){
			teacher.setWorkloadAllocated(0);
		}

		teacher.setIdSchool(userLoggedUtils.getSchoolUserLogged());

		String enconderPassword = new BCryptPasswordEncoder().encode(dto.password());
		teacher.setPassword(enconderPassword);

		teacher.setRegistration(new RegistrationGenerator()
				.generateRegistrationUnique(teacher.getRegistration()));
	}
}