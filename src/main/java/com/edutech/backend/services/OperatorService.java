package com.edutech.backend.services;

import com.edutech.backend.dtos.operator.OperatorRequestDTO;
import com.edutech.backend.dtos.operator.OperatorResponseDTO;
import com.edutech.backend.entities.Operator;
import com.edutech.backend.exceptions.ExistingResourceException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.OperatorMapper;
import com.edutech.backend.repositories.OperatorRepository;
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
public class OperatorService {

	private final OperatorRepository repositoryOperator;
	private final OperatorMapper mapperOperator;
	private final UserLoggedUtils userLoggedUtils;

	public List<OperatorResponseDTO> findAll() {
		return repositoryOperator.findAll().stream().map(OperatorResponseDTO::new).toList();
	}

	@Transactional
	public Operator create(OperatorRequestDTO dto) {
		Operator newOperator = mapperOperator.toEntity(dto);
		prepareCreateOperator(newOperator, dto);
		return repositoryOperator.save(newOperator);
	}

	@Transactional
	public Operator update(Long id, OperatorRequestDTO dto) {
		try {
			Operator entity = repositoryOperator.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Operador com ID: " + id + " não existe."));

			mapperOperator.updateOperatorFromDTO(dto, entity);
			return repositoryOperator.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void delete(Long id) {
		Operator operator = repositoryOperator.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Operador com ID: " + id + " não existe."));
		repositoryOperator.delete(operator);
	}

	private void prepareCreateOperator(Operator operator, OperatorRequestDTO dto) {
		ValidatorUtils.validateName(dto.name());
		ValidatorUtils.validateEmail(dto.email());

		if (repositoryOperator.findByEmail(dto.email()).isPresent()) {
			throw new ExistingResourceException("Email já cadastrado ");
		}

		operator.setIdSchool(userLoggedUtils.getSchoolUserLogged());

		String enconderPassword = new BCryptPasswordEncoder().encode(dto.password());
		operator.setPassword(enconderPassword);

		operator.setRegistration(new RegistrationGenerator()
				.generateRegistrationUnique(operator.getRegistration()));

	}
}