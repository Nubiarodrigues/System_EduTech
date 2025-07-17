package com.edutech.backend.services;

import com.edutech.backend.dtos.bimestergrade.BimesterGradeFinalRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeResponseDTO;
import com.edutech.backend.entities.BimesterGrade;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Student;
import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.BimesterGradeMapper;
import com.edutech.backend.repositories.BimesterGradeRepository;
import com.edutech.backend.repositories.DisciplineRepository;
import com.edutech.backend.repositories.StudentRepository;
import com.edutech.backend.repositories.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BimesterGradeService {

    private final BimesterGradeRepository repositoryBimesterGrade;
    private final DisciplineRepository repositoryDiscipline;
    private final TeacherRepository repositoryTeacher;
    private final StudentRepository repositoryStudent;
    private final BimesterGradeMapper mapperBimesterGrade;


    public List<BimesterGradeResponseDTO> findAll() {
        return repositoryBimesterGrade.findAll().stream().map(BimesterGradeResponseDTO::new).collect(Collectors.toList());
    }

    public BimesterGrade findById(Long id) {
        BimesterGrade bimester = repositoryBimesterGrade.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return bimester;
    }


    @Transactional
    public BimesterGrade create(Long disciplineId, BimesterGradeRequestDTO dto, Long teacherId, Long studentId) {
        BimesterGrade newBimesterGrade = mapperBimesterGrade.toEntity(dto);

        Student student = repositoryStudent.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("O aluno não existe."));

        Discipline discipline = repositoryDiscipline.findById(disciplineId)
                .orElseThrow(() -> new EntityNotFoundException("A disciplina não existe."));

        newBimesterGrade.setSituation(newBimesterGrade.defineSituationNoFinal(newBimesterGrade));
        newBimesterGrade.setStudent(student);
        newBimesterGrade.setDiscipline(discipline);
        repositoryBimesterGrade.save(newBimesterGrade);

        return repositoryBimesterGrade.findById(newBimesterGrade.getId()).orElseThrow(() -> new ResourceNotFoundException(newBimesterGrade.getId()));
    }


    @Transactional
    public BimesterGrade update(Long id, BimesterGradeRequestDTO dto, Long teacherId, Long studentId) {
        try {
            BimesterGrade current = repositoryBimesterGrade.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

            Student currentStudent = repositoryStudent.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException("O aluno com ID: " + studentId + " não existe."));

            if (!current.getStudent().getId().equals(currentStudent.getId())) {
                throw new InvalidDataException("A operação não pode ser concluída: o aluno informado no update é diferente do aluno associado ao registro original.");
            }

            mapperBimesterGrade.updateBimesterFromDTO(dto, current);
            return repositoryBimesterGrade.save(current);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public BimesterGrade updateFinal(Long id, BimesterGradeFinalRequestDTO dto, Long teacherId, Long studentId) {
        try {
            BimesterGrade current = repositoryBimesterGrade.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

            Student currentStudent = repositoryStudent.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException("O aluno com ID: " + studentId + " não existe."));

            if (!current.getStudent().getId().equals(currentStudent.getId())) {
                throw new InvalidDataException("A operação não pode ser concluída: o aluno informado no update é diferente do aluno associado ao registro original.");
            }

            current.setGradeFinal(dto.gradeFinal());
            current.setSituation(current.defineSituationFinal(current));

            mapperBimesterGrade.updateBimesterFinalFromDTO(dto, current);
            return repositoryBimesterGrade.save(current);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}