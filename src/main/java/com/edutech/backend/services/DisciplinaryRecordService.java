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
import jakarta.persistence.EntityNotFoundException;
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
    public DisciplinaryRecord create(DisciplinaryRecordRequestDTO dto, Long idStudent, String responsible){
        DisciplinaryRecord newDisciplinaryRecord = mapperDisciplinaryRecord.toEntity(dto);

        Student student = repositoryStudent.findById(idStudent)
                .orElseThrow(() -> new ResourceNotFoundException("O aluno não existe."));

        newDisciplinaryRecord.setStudent(student);
        newDisciplinaryRecord.setResponsible(responsible);

        verifyLimitWarningsAndSuspensions(newDisciplinaryRecord, idStudent);
        repositoryDisciplinaryRecord.save(newDisciplinaryRecord);
        return newDisciplinaryRecord;
    }

    private void verifyLimitWarningsAndSuspensions(DisciplinaryRecord entity, Long idStudent) {

        List<DisciplinaryRecord> previousRecords = repositoryDisciplinaryRecord.findByStudentId(idStudent);

        int warningsVerbal = previousRecords.stream()
                .filter(r -> r.getOccurrence() == TypeOccurrence.LEVE)
                .mapToInt(DisciplinaryRecord::getVerbalWarnings).sum();

        int warningsFormal = previousRecords.stream()
                .filter(r -> r.getOccurrence() == TypeOccurrence.ADVERTENCIA)
                .mapToInt(DisciplinaryRecord::getWarningsIssued).sum();

        int warningsSuspension = previousRecords.stream()
                .filter(r -> r.getOccurrence() == TypeOccurrence.SUSPENSAO)
                .mapToInt(DisciplinaryRecord::getSuspensionsIssued).sum();

        if (entity.getOccurrence().equals(TypeOccurrence.LEVE)) {
            if ((warningsVerbal + 1) > 2) {
                throw new InvalidDataException("O limite de advertências verbais foi excedido.");
            }
            entity.setVerbalWarnings(1);
        }

        if (entity.getOccurrence().equals(TypeOccurrence.ADVERTENCIA)) {
            if ((warningsFormal + 1) > 3) {
                throw new InvalidDataException("O limite de advertências formais foi excedido. É necessário buscar ajuda do Conselho Tutelar.");
            }
            entity.setWarningsIssued(1);
        }

        if (entity.getOccurrence().equals(TypeOccurrence.SUSPENSAO)) {
            if ((warningsSuspension + 1) > 3) {
                entity.setExpulsion(true);
                throw new InvalidDataException("O limite de suspensões formais foi excedido. Nesta etapa, não há mais o que fazer.");
            }
            entity.setSuspensionsIssued(1);
        }
    }

    @Transactional
    public DisciplinaryRecord update(Long id, Long idStudent, DisciplinaryRecordRequestDTO dto, String responsible){
        try {
            DisciplinaryRecord current = repositoryDisciplinaryRecord.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Ocorrência com ID: " + id + " não existe."));

            mapperDisciplinaryRecord.updateDisciplinaryRecordFromDTO(dto, current);

            if(!current.getResponsible().equals(responsible)){
               throw new InvalidDataException("Você não tem permissão para atualizar este recurso.");
            }

            return repositoryDisciplinaryRecord.save(current);
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public void delete(Long id, String responsible){
        DisciplinaryRecord disciplinaryRecord = repositoryDisciplinaryRecord.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrência com ID: " + id + " não existe."));

        if(!disciplinaryRecord.getResponsible().equals(responsible)){
            throw new InvalidDataException("Você não tem permissão para atualizar este recurso.");
        }

        repositoryDisciplinaryRecord.delete(disciplinaryRecord);
    }
}