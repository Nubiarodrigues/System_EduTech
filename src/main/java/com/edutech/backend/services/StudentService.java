package com.edutech.backend.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.dtos.StudentResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Student;
import com.edutech.backend.exceptions.ExistingResourceException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.StudentMapper;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.StudentRepository;
import com.edutech.backend.utils.RegistrationGenerator;
import com.edutech.backend.utils.ValidatorUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository repositoryStudent;
	private final StudentMapper mapperStudent;
	private final ClassroomRepository repositoryClassroom;
	private final CepService serviceCep;

	public List<StudentResponseDTO> findAll() {
		return repositoryStudent.findAll().stream().map(StudentResponseDTO::new).toList();
	}

	public Student findById(Long id) {
		return repositoryStudent.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Student createStudent(StudentRequestDTO dto) {
		Student entity = mapperStudent.toEntity(dto);
		prepareCreateStudent(entity, dto);
		return repositoryStudent.save(entity);
	}

	@Transactional
	public Student updateStudent(Long id, StudentRequestDTO obj) {
		try {
			Student entity = repositoryStudent.getReferenceById(id);
			mapperStudent.updateStudentFromDTO(obj, entity);
			return repositoryStudent.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteStudent(Long id) {
		Student student = repositoryStudent.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryStudent.delete(student);
	}

	private void prepareCreateStudent(Student student, StudentRequestDTO dto) {
		ValidatorUtils.validateName(dto.name());
		ValidatorUtils.validateEmail(dto.email());
		ValidatorUtils.validateCpf(dto.cpf());
		ValidatorUtils.validateTelephone(dto.telephone());
		ValidatorUtils.validateCep(dto.cep());
		ValidatorUtils.validateSus(dto.sus());
		ValidatorUtils.validateName(dto.motherName());
		ValidatorUtils.validateName(dto.fatherName());
		ValidatorUtils.validateBirthDate(dto.dateBirth());
		ValidatorUtils.validateEmailResponsable(dto.emailResponsable());

		Classroom classroom = repositoryClassroom.findById(dto.classroomId())
				.orElseThrow(() -> new ResourceNotFoundException("Turma não existe"));

		if (repositoryStudent.findByEmail(dto.email()).isPresent()) {
			throw new ExistingResourceException("E-mail já cadastrado.");
		}

		student.setClassroom(classroom);

		String enconderPassword = new BCryptPasswordEncoder().encode(dto.password());
		student.setPassword(enconderPassword);

		student.setRegistration(new RegistrationGenerator()
				.generateRegistrationUniqueStudent(student.getRegistration()));

		student.setAddress(serviceCep.findAdress(dto.cep()));

	}

}
