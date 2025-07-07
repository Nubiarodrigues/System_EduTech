package com.edutech.backend.services;

import com.edutech.backend.dtos.DisciplineRequestDTO;
import com.edutech.backend.dtos.DisciplineResponseDTO;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.DisciplineMapper;
import com.edutech.backend.repositories.DisciplineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository repositoryDiscipline;
    private final DisciplineMapper mapperDiscipline;

    public List<DisciplineResponseDTO> findAll(){
        return repositoryDiscipline.findAll().stream().map(DisciplineResponseDTO::new).toList();
    }

    public Discipline createDiscipline(DisciplineRequestDTO dto){
        Discipline newDiscipline = mapperDiscipline.toEntity(dto);
        return  repositoryDiscipline.save(newDiscipline);
    }

    public Discipline updateDiscipline(Long id, DisciplineRequestDTO dto){
        try {
            Discipline entity = repositoryDiscipline.getReferenceById(id);
            mapperDiscipline.updateDisciplineFromDTO(dto, entity);;
            return repositoryDiscipline.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void deleteDiscipline(Long id){
        Discipline discipline = repositoryDiscipline.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        repositoryDiscipline.delete(discipline);
    }

}
