package com.edutech.backend.services;

import com.edutech.backend.dtos.DisciplineRequestDTO;
import com.edutech.backend.dtos.DisciplineResponseDTO;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.DisciplineMapper;
import com.edutech.backend.repositories.DisciplineRepository;
import com.edutech.backend.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository repositoryDiscipline;
    private final DisciplineMapper mapperDiscipline;
    private final TeacherRepository repositoryTeacher;

    public List<DisciplineResponseDTO> findAll() {
        return repositoryDiscipline.findAll().stream().map(DisciplineResponseDTO::new).toList();
    }

    public Discipline findById(Long id) {
        return repositoryDiscipline.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Discipline createDiscipline(DisciplineRequestDTO dto) {
        Discipline newDiscipline = mapperDiscipline.toEntity(dto);

        Teacher teacher = repositoryTeacher.findById(dto.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado!"));

        newDiscipline.setTeacher(teacher);
        return repositoryDiscipline.save(newDiscipline);
    }

    public Discipline updateDiscipline(Long id, DisciplineRequestDTO dto) {
        try {
            Discipline entity = repositoryDiscipline.getReferenceById(id);
            mapperDiscipline.updateDisciplineFromDTO(dto, entity);
            ;
            return repositoryDiscipline.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void deleteDiscipline(Long id) {
        Discipline discipline = repositoryDiscipline.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        repositoryDiscipline.delete(discipline);
    }
}
