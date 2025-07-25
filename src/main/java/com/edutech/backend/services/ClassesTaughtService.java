package com.edutech.backend.services;

import com.edutech.backend.dtos.classestaught.ClassesTaughtRequestDTO;
import com.edutech.backend.dtos.classestaught.ClassesTaughtResponseDTO;
import com.edutech.backend.entities.ClassesTaught;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.ClassesTaughtMapper;
import com.edutech.backend.repositories.ClassesTaughtRepository;
import com.edutech.backend.repositories.DisciplineRepository;
import com.edutech.backend.repositories.TeacherRepository;
import com.edutech.backend.utils.UserLoggedUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    private final UserLoggedUtils  userLoggedUtils;

    public List<ClassesTaughtResponseDTO> findAll() {
        return repositoryClassesTaught.findAll().stream().map(ClassesTaughtResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public ClassesTaught create(Long disciplineId, ClassesTaughtRequestDTO dto, Long teacherId) {
        Teacher teacher = repositoryTeacher.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Professor com ID: " + teacherId + " não existe."));

        ClassesTaught classesTaught = mapperclassesTaught.toEntity(dto);

        classesTaught.setTeacher(teacher);

        Discipline discipline = repositoryDiscipline.findById(disciplineId)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina com ID " + disciplineId + " não encontrada!"));

        classesTaught.setIdSchool(userLoggedUtils.getSchoolUserLogged());
        classesTaught.setDiscipline(discipline);

        repositoryClassesTaught.save(classesTaught);
        return classesTaught;
    }

    @Transactional
    public ClassesTaught update(Long id, ClassesTaughtRequestDTO dto, Long teacherId) {
        try {
            ClassesTaught registerClasses = repositoryClassesTaught.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Registro de aula com ID: " + id + " não encontrado."));

            Teacher teacher = repositoryTeacher.findById(teacherId)
                    .orElseThrow(() -> new ResourceNotFoundException("Professor com ID: " + teacherId + " não encontrado."));

            if(!registerClasses.getTeacher().getId().equals(teacher.getId())){
                throw new InvalidDataException("Você não tem permissão para alterar este registro.");
            }
            mapperclassesTaught.updateClassesTaughtFromDTO(dto, registerClasses);
            return repositoryClassesTaught.save(registerClasses);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
