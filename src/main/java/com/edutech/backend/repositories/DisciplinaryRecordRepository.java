package com.edutech.backend.repositories;

import com.edutech.backend.entities.DisciplinaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaryRecordRepository extends JpaRepository<DisciplinaryRecord,Long> {

    List<DisciplinaryRecord> findByStudentId(Long id);

}
