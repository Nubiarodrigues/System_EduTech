package com.edutech.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edutech.backend.dtos.AdministratorRequestDTO;
import com.edutech.backend.dtos.AdministratorResponseDTO;
import com.edutech.backend.entities.Administrator;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.AdministratorMapper;
import com.edutech.backend.repositories.AdministratorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdministratorService {

	private final AdministratorRepository repositoryAdministrator;
	private final AdministratorMapper mapperAdministrator;

	public List<AdministratorResponseDTO> findAll() {
		return repositoryAdministrator.findAll().stream().map(AdministratorResponseDTO::new).toList();
	}

	@Transactional
	public Administrator createAdmin(AdministratorRequestDTO dto) {
		Administrator newAdministrator = mapperAdministrator.toEntity(dto);
		return repositoryAdministrator.save(newAdministrator);
	}

	@Transactional
	public Administrator updateAdmin(Long id, AdministratorRequestDTO dto) {
		try {
			Administrator entity = repositoryAdministrator.getReferenceById(id);
			mapperAdministrator.updateAdministratorFromDTO(dto, entity);
			return repositoryAdministrator.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@Transactional
	public void deleteAdmin(Long id) {
		Administrator administrator = repositoryAdministrator.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		repositoryAdministrator.delete(administrator);
	}

}
