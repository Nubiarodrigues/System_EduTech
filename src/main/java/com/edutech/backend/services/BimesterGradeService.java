package com.edutech.backend.services;

import com.edutech.backend.dtos.bimestergrade.BimesterGradeRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeResponseDTO;
import com.edutech.backend.entities.BimesterGrade;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Student;
import com.edutech.backend.entities.Teacher;
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


    public List<BimesterGradeResponseDTO> findAll(){
        return repositoryBimesterGrade.findAll().stream().map(BimesterGradeResponseDTO::new).collect(Collectors.toList());
    }


    @Transactional
    public BimesterGrade create(Long disciplineId, BimesterGradeRequestDTO dto, Long teacherId, Long studentId) {
        BimesterGrade newBimesterGrade = mapperBimesterGrade.toEntity(dto);

        Student student = repositoryStudent.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("O aluno não existe."));

        Discipline discipline = repositoryDiscipline.findById(disciplineId)
                .orElseThrow(() -> new EntityNotFoundException("A disciplina não existe."));

        if(discipline.getTeacher().getId().equals(teacherId)) {
            Teacher teacher = repositoryTeacher.findById(teacherId)
                    .orElseThrow(() -> new ResourceNotFoundException(teacherId));
        } else {
            throw new InvalidDataException("Você não tem permissão para adicionar notas em uma disciplina que não esta alocado.");
        }

        newBimesterGrade.setStudent(student);
        newBimesterGrade.setDiscipline(discipline);
        return repositoryBimesterGrade.save(newBimesterGrade);
    }

}
