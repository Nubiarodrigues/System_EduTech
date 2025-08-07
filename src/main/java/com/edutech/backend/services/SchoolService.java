package com.edutech.backend.services;

import com.edutech.backend.dtos.school.SchoolRequestDTO;
import com.edutech.backend.dtos.school.SchoolRequestSimpleDTO;
import com.edutech.backend.entities.School;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.SchoolMapper;
import com.edutech.backend.repositories.SchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repositorySchool;
    private final SchoolMapper mapperSchool;
    private final CepService serviceCep;


    public School findById(Long id){
        return repositorySchool.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escola com o ID: " + id + " não existe."));
    }

    @Transactional
    public School create(SchoolRequestDTO dto) {
        School newSchool = mapperSchool.toEntity(dto);
        newSchool.setAddress(serviceCep.findAddress(dto.cep()));
        return repositorySchool.save(newSchool);
    }

    @Transactional
    public School update(Long id, SchoolRequestDTO dto) {
        try {
            School current = repositorySchool.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Escola com o ID: " + id + " não existe."));

            mapperSchool.updateSchoolFromDTO(dto, current);
            return repositorySchool.save(current);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public School updatePartial(Long id, SchoolRequestSimpleDTO dto){
        try {
            School current = repositorySchool.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Escola com o ID: " + id + " não existe."));

            current.setStages(dto.stages());

            mapperSchool.partialSchoolFromDTO(dto, current);
            return repositorySchool.save(current);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id){
        School school = repositorySchool.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Escola com o ID: " + id + " não existe."));
        repositorySchool.delete(school);
    }
}