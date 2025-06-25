package com.edutech.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.backend.dtos.OperatorRequestDTO;
import com.edutech.backend.dtos.OperatorResponseDTO;
import com.edutech.backend.entities.Operator;
import com.edutech.backend.exceptions.ExistingResourceException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.OperatorMapper;
import com.edutech.backend.repositories.OperatorRepository;
import com.edutech.backend.utils.RegistrationGenerator;
import com.edutech.backend.utils.ValidatorUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperatorService {

	private final OperatorRepository repositoryOperator;
	private final OperatorMapper mapperOperator;

	public List<OperatorResponseDTO> findAll() {
		return repositoryOperator.findAll().stream().map(OperatorResponseDTO::new).toList();
	}

	@Transactional
	public Operator createOperator(OperatorRequestDTO dto) {
		Operator newOperator = mapperOperator.toEntity(dto);
		prepareCreateOperator(newOperator, dto);
		return repositoryOperator.save(newOperator);
	}

	@Transactional
	public Operator updateOperator(Long id, OperatorRequestDTO dto) {
		try {
			Operator entity = repositoryOperator.getReferenceById(id);
			mapperOperator.updateOperatorFromDTO(dto, entity);
			return repositoryOperator.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteOperator(Long id) {
		Operator operator = repositoryOperator.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryOperator.deleteById(id);
	}

	private void prepareCreateOperator(Operator operator, OperatorRequestDTO dto) {
		ValidatorUtils.validateEmail(dto.email());

		if (repositoryOperator.findByEmail(dto.email()).isPresent()) {
			throw new ExistingResourceException("Email já cadastrado ");
		}

		operator.setRegistration(new RegistrationGenerator()
				.generateRegistrationUnique(operator.getRegistration()));

	}

}
