package com.edutech.backend.controllers;

import com.edutech.backend.dtos.disciplinaryrecord.DisciplinaryRecordRequestDTO;
import com.edutech.backend.dtos.disciplinaryrecord.DisciplinaryRecordResponseDTO;
import com.edutech.backend.entities.DisciplinaryRecord;
import com.edutech.backend.entities.User;
import com.edutech.backend.mapper.DisciplinaryRecordMapper;
import com.edutech.backend.services.DisciplinaryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/historys")
@RequiredArgsConstructor
public class DisciplinaryRecordController {

    private final DisciplinaryRecordService servicedisciplinaryRecord;
    private final DisciplinaryRecordMapper mapperDisciplinaryRecord;

    @GetMapping
    public ResponseEntity<List<DisciplinaryRecordResponseDTO>> findAll(){
        List<DisciplinaryRecordResponseDTO> history = servicedisciplinaryRecord.findAll();
        return ResponseEntity.ok(history);
    }

    @PostMapping
    public ResponseEntity<DisciplinaryRecordResponseDTO> create (@RequestBody DisciplinaryRecordRequestDTO dto, @AuthenticationPrincipal User user){
        String responsible = user.getName();
        DisciplinaryRecord newDisciplinary = servicedisciplinaryRecord.create(dto,responsible);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDisciplinary.getId())
                .toUri();
        DisciplinaryRecordResponseDTO response = mapperDisciplinaryRecord.toResponseDTO(newDisciplinary);
        return ResponseEntity.created(location).body(response);
    }

}
