package com.edutech.backend.services;

import com.edutech.backend.dtos.discipline.DisciplineRequestDTO;
import com.edutech.backend.dtos.discipline.DisciplineResponseDTO;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.DisciplineMapper;
import com.edutech.backend.repositories.DisciplineRepository;
import com.edutech.backend.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
        return repositoryDiscipline.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina com ID: " + id + " não existe."));
    }

    @Transactional
    public Discipline create(DisciplineRequestDTO dto) {
        Discipline newDiscipline = mapperDiscipline.toEntity(dto);
        return repositoryDiscipline.save(newDiscipline);
    }

    @Transactional
    public Discipline update(Long id, DisciplineRequestDTO dto) {
        try {
            Discipline entity = repositoryDiscipline.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Disciplina com ID: " + id + " não existe."));

            mapperDiscipline.updateDisciplineFromDTO(dto, entity);
            return repositoryDiscipline.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id) {
        Discipline discipline = repositoryDiscipline.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina com ID: " + id + " não existe."));
        repositoryDiscipline.delete(discipline);
    }

    @Transactional
    public void allocateTeacher (Long disciplineId, Long teacherId) {
        Discipline discipline = repositoryDiscipline.findById(disciplineId)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina com ID: " + disciplineId + " não existe."));

        Teacher teacher = repositoryTeacher.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor com ID: " + teacherId + " não existe."));

        if(teacher.getWorkloadAllocated() < teacher.getWorkloadTotal()) {
            teacher.setWorkloadAllocated(teacher.getWorkloadAllocated() + discipline.getWorkload());
            discipline.setTeacher(teacher);
        } else {
            throw new InvalidDataException("Carga horária excedida.");
        }

        repositoryDiscipline.save(discipline);
    }
}