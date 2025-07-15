package com.edutech.backend.services;

import com.edutech.backend.dtos.schoolnotice.SchoolNoticesRequestDTO;
import com.edutech.backend.dtos.schoolnotice.SchoolNoticesResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.SchoolNotices;
import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.SchoolNoticesMapper;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.SchoolNoticesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolNoticesService {

    private final SchoolNoticesRepository repositorySchoolNotices;
    private final ClassroomRepository repositoryClassroom;
    private final SchoolNoticesMapper mapperSchoolNotices;

    public List<SchoolNoticesResponseDTO> findAll(){
        return repositorySchoolNotices.findAll().stream().map(SchoolNoticesResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public SchoolNotices create(SchoolNoticesRequestDTO dto, Long idClassroom, String authorName){
        SchoolNotices newSchoolNotices = mapperSchoolNotices.toEntity(dto);
        newSchoolNotices.setAuthor(authorName);

        Classroom classroomCurrent = repositoryClassroom.findById(idClassroom)
                .orElseThrow(() -> new ResourceNotFoundException("A turma não existe."));

        if(newSchoolNotices.getClassrooms() == null){
            newSchoolNotices.setClassrooms(new ArrayList<>());
        }

        newSchoolNotices.getClassrooms().add(classroomCurrent);

        return repositorySchoolNotices.save(newSchoolNotices);
    }

    @Transactional
    public SchoolNotices update(Long id, SchoolNoticesRequestDTO dto, String authorName){
        try {
            SchoolNotices current = repositorySchoolNotices.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Aviso com o ID: " + id + " não existe."));

            if(!authorName.equals(current.getAuthor())){
                throw new InvalidDataException("Você não tem permissão para alterar este aviso.");
            }

            mapperSchoolNotices.updateSchoolNoticesFromDTO(dto, current);
            return repositorySchoolNotices.save(current);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id, String authorName){
        SchoolNotices schoolNotices = repositorySchoolNotices.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if(!authorName.equals(schoolNotices.getAuthor())){
            throw new InvalidDataException("Você não tem permissão para alterar este aviso.");
        }
        repositorySchoolNotices.delete(schoolNotices);
    }
}