package com.edutech.backend.services;

import com.edutech.backend.dtos.frequency.FrequencyRequestDTO;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Frequency;
import com.edutech.backend.entities.Student;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.FrequencyMapper;
import com.edutech.backend.repositories.DisciplineRepository;
import com.edutech.backend.repositories.FrequencyRepository;
import com.edutech.backend.repositories.StudentRepository;
import com.edutech.backend.utils.UserLoggedUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FrequencyService {

    private final FrequencyRepository repositoryFrequency;
    private final StudentRepository repositoryStudent;
    private final DisciplineRepository repositoryDiscipline;
    private final FrequencyMapper mapperFrequency;
    private final UserLoggedUtils  userLoggedUtils;

    @Transactional
    public Frequency create(FrequencyRequestDTO dto, Long idStudent, Long idDiscipline) {
        Frequency newFrequency = mapperFrequency.toEntity(dto);

        Student student = repositoryStudent.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("Aluno com ID: " + idStudent + " não existe."));
        newFrequency.setStudent(student);

        Discipline discipline = repositoryDiscipline.findById(idDiscipline)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina com ID: " + idDiscipline + " não existe."));
        newFrequency.setDiscipline(discipline);

        newFrequency.setIdSchool(userLoggedUtils.getSchoolUserLogged());
        percentageDay(dto.presenceDay(), newFrequency);
        return repositoryFrequency.save(newFrequency);
    }

    private Double percentageDay(List<Integer> presenceDay, Frequency entity) {

        int totalPresence = presenceDay.stream()
                .mapToInt(Integer::intValue)
                .sum();

        double percentage = ((double) totalPresence / entity.getClasses()) * 100.0;

        entity.setPercentageDay(percentage);

        return percentage;
    }
}

