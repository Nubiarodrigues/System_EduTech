package com.edutech.backend.services;

import com.edutech.backend.dtos.coordinator.CoordinatorRequestDTO;
import com.edutech.backend.dtos.coordinator.CoordinatorResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.exceptions.ExistingResourceException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.CoordinatorMapper;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.CoordinatorRepository;
import com.edutech.backend.utils.RegistrationGenerator;
import com.edutech.backend.utils.ValidatorUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordinatorService {

	private final CoordinatorRepository repositoryCoordinator;
	private final CoordinatorMapper mapperCoordinator;
	private final ClassroomRepository repositoryClassroom;
	private final CepService serviceCep;

	public List<CoordinatorResponseDTO> findAll() {
		return repositoryCoordinator.findAll().stream().map(CoordinatorResponseDTO::new).toList();
	}

	public Coordinator findById(Long id) {
		return repositoryCoordinator.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Coordenador com ID: " + id + " não existe."));
	}

	@Transactional
	public Coordinator create(CoordinatorRequestDTO dto) {
		Coordinator newCoordinator = mapperCoordinator.toEntity(dto);
		prepareCreateCoordinator(newCoordinator, dto);
		return repositoryCoordinator.save(newCoordinator);
	}

	@Transactional
	public Coordinator update(Long id, CoordinatorRequestDTO dto) {
		try {
			Coordinator entity = repositoryCoordinator.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Coordenador com ID: " + id + " não existe."));

			mapperCoordinator.updateCoordinatorFromDTO(dto, entity);
			return repositoryCoordinator.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void delete(Long id) {
		Coordinator coordinator = repositoryCoordinator.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Coordenador com ID: " + id + " não existe."));
		repositoryCoordinator.delete(coordinator);
	}

	public List<Classroom> getClassroomByModality(Long id) {
		Coordinator coordinator = repositoryCoordinator.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		return repositoryClassroom.findByModality(coordinator.getModality());
	}

	private void prepareCreateCoordinator(Coordinator coordinator, CoordinatorRequestDTO dto) {
		ValidatorUtils.validateName(dto.name());
		ValidatorUtils.validateBirthDate(dto.dateBirth());
		ValidatorUtils.validateEmail(dto.email());
		ValidatorUtils.validateCpf(dto.cpf());
		ValidatorUtils.validateTelephone(dto.telephone());
		ValidatorUtils.validateCep(dto.cep());
		
		if (repositoryCoordinator.findByEmail(dto.email()).isPresent()) {
			throw new ExistingResourceException("E-mail já cadastrado.");
		}

		String enconderPassword = new BCryptPasswordEncoder().encode(dto.password());
		coordinator.setPassword(enconderPassword);
 
		coordinator.setRegistration(new RegistrationGenerator()
						.generateRegistrationUnique(coordinator.getRegistration()));

		coordinator.setAddress(serviceCep.findAddress(dto.cep()));
	}
}
