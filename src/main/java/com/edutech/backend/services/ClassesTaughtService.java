package com.edutech.backend.services;

import com.edutech.backend.dtos.ClassesTaughtRequestDTO;
import com.edutech.backend.dtos.ClassesTaughtResponseDTO;
import com.edutech.backend.entities.ClassesTaught;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.entities.User;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.ClassesTaughtMapper;
import com.edutech.backend.repositories.ClassesTaughtRepository;
import com.edutech.backend.repositories.DisciplineRepository;
import com.edutech.backend.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassesTaughtService {

    private final ClassesTaughtRepository repositoryClassesTaught;
    private final ClassesTaughtMapper mapperclassesTaught;
    private final TeacherRepository repositoryTeacher;
    private final DisciplineRepository repositoryDiscipline;

    public List<ClassesTaughtResponseDTO> findAll() {
        return repositoryClassesTaught.findAll().stream().map(ClassesTaughtResponseDTO::new).collect(Collectors.toList());
    }

    public ClassesTaught findById(Long id) {
        return repositoryClassesTaught.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public ClassesTaught createRegister(Long disciplineId, ClassesTaughtRequestDTO dto, @AuthenticationPrincipal User user) {
        Long teacherId = user.getId();

        Teacher teacher = repositoryTeacher.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado!"));

        ClassesTaught classesTaught = mapperclassesTaught.toEntity(dto);

        classesTaught.setTeacher(teacher);

        Discipline discipline = repositoryDiscipline.findById(disciplineId)
                .orElseThrow(() -> new ResourceNotFoundException("Discipline com ID " + disciplineId + " não encontrada!"));

        classesTaught.setDiscipline(discipline);

        repositoryClassesTaught.save(classesTaught);
        return classesTaught;
    }


    @Transactional
    public ClassesTaught updateRegister(Long id, ClassesTaughtRequestDTO dto) {
        try {
            ClassesTaught classesTaught = repositoryClassesTaught.getReferenceById(id);
            mapperclassesTaught.updateClassesTaughtFromDTO(dto, classesTaught);
            return repositoryClassesTaught.save(classesTaught);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
