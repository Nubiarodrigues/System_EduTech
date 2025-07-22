package com.edutech.backend.services;

import com.edutech.backend.dtos.disciplinaryrecord.DisciplinaryRecordRequestDTO;
import com.edutech.backend.dtos.disciplinaryrecord.DisciplinaryRecordResponseDTO;
import com.edutech.backend.entities.DisciplinaryRecord;
import com.edutech.backend.entities.Student;
import com.edutech.backend.enuns.TypeOccurrence;
import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.exceptions.ResourceNotFoundException;
import com.edutech.backend.mapper.DisciplinaryRecordMapper;
import com.edutech.backend.repositories.DisciplinaryRecordRepository;
import com.edutech.backend.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplinaryRecordService {

    private final DisciplinaryRecordRepository repositoryDisciplinaryRecord;
    private final StudentRepository repositoryStudent;
    private final DisciplinaryRecordMapper mapperDisciplinaryRecord;


    public List<DisciplinaryRecordResponseDTO> findAll(){
        return repositoryDisciplinaryRecord.findAll()
                .stream().map(DisciplinaryRecordResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public DisciplinaryRecord create(DisciplinaryRecordRequestDTO dto, String responsible){
        DisciplinaryRecord newDisciplinaryRecord = mapperDisciplinaryRecord.toEntity(dto);

        Student student = repositoryStudent.findById(dto.idStudent())
                .orElseThrow(() -> new ResourceNotFoundException("O aluno não existe."));

        newDisciplinaryRecord.setStudent(student);
        newDisciplinaryRecord.setResponsible(responsible);

        verifyLimitWarningsAndSuspensions(newDisciplinaryRecord);
        repositoryDisciplinaryRecord.save(newDisciplinaryRecord);
        return newDisciplinaryRecord;
    }

    private void verifyLimitWarningsAndSuspensions(DisciplinaryRecord entity){

        if(entity.getVerbalWarnings() < 2){
            if(entity.getOccurrence().equals(TypeOccurrence.LEVE)){
                entity.setVerbalWarnings(entity.getVerbalWarnings() + 1);
            }
        } else {
            throw new InvalidDataException("O limite de advertências verbais foi excedido.");
        }

        if(entity.getWarningsIssued() < 3){
            if(entity.getOccurrence().equals(TypeOccurrence.ADVERTENCIA)){
                entity.setWarningsIssued(entity.getWarningsIssued() + 1);
            }
        } else {
            throw new InvalidDataException("O limite de advertências formais foi excedido. É necessário buscar ajuda do Conselho Tutelar.");
        }

        if(entity.getSuspensionsIssued() < 3){
            if(entity.getOccurrence().equals(TypeOccurrence.SUSPENSAO)){
                entity.setSuspensionsIssued(entity.getSuspensionsIssued() + 1);
            }
        } else {
            throw new InvalidDataException("O limite de suspensões formais foi excedido. Nesta etapa, não há mais o que fazer.");
        }

        entity.setExpulsion(true);
    }
}
