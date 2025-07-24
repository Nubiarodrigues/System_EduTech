package com.edutech.backend.services;

import com.edutech.backend.dtos.administrator.AdministratorRequestDTO;
import com.edutech.backend.dtos.administrator.AdministratorResponseDTO;
import com.edutech.backend.entities.Administrator;
import com.edutech.backend.exceptions.ExistingResourceException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.AdministratorMapper;
import com.edutech.backend.repositories.AdministratorRepository;
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
public class AdministratorService {

	private final AdministratorRepository repositoryAdministrator;
	private final AdministratorMapper mapperAdministrator;

	public List<AdministratorResponseDTO> findAll() {
		return repositoryAdministrator.findAll().stream().map(AdministratorResponseDTO::new).toList();
	}

	@Transactional
	public Administrator create(AdministratorRequestDTO dto) {
		Administrator newAdministrator = mapperAdministrator.toEntity(dto);
		prepareCreateAdministrator(newAdministrator, dto);
		return repositoryAdministrator.save(newAdministrator);
	}

	@Transactional
	public Administrator update(Long id, AdministratorRequestDTO dto) {
		try {
			Administrator current = repositoryAdministrator
					.findById(id).orElseThrow(() -> new ResourceNotFoundException("Administrator com ID: " + id + " não existe."));

			mapperAdministrator.updateAdministratorFromDTO(dto, current);
			return repositoryAdministrator.save(current);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void delete(Long id) {
		Administrator administrator = repositoryAdministrator.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Administrator com ID: " + id + " não existe."));
		repositoryAdministrator.delete(administrator);
	}
	
	
	private void prepareCreateAdministrator(Administrator admin, AdministratorRequestDTO dto) {
		ValidatorUtils.validateName(dto.name());
		ValidatorUtils.validateEmail(dto.email());
		
		if(repositoryAdministrator.findByEmail(dto.email()).isPresent()) {
			throw new ExistingResourceException("Email já cadastrado. ");
		}

		String enconderPassword = new BCryptPasswordEncoder().encode(dto.password());
		admin.setPassword(enconderPassword);
		
		admin.setRegistration(new RegistrationGenerator()
				.generateRegistrationUnique(admin.getRegistration()));
		
	}
}
