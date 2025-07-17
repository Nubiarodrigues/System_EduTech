package com.edutech.backend.controllers;

import com.edutech.backend.dtos.bimestergrade.BimesterGradeFinalRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeResponseDTO;
import com.edutech.backend.entities.BimesterGrade;
import com.edutech.backend.entities.User;
import com.edutech.backend.mapper.BimesterGradeMapper;
import com.edutech.backend.services.BimesterGradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bimester-grade")
@RequiredArgsConstructor
public class BimesterGradeController {

    private final BimesterGradeService serviceBimesterGrade;
    private final BimesterGradeMapper mapperBimesterGradeMapper;

    @GetMapping
    public ResponseEntity<List<BimesterGradeResponseDTO>> findAll(){
        List<BimesterGradeResponseDTO> bimesters = serviceBimesterGrade.findAll();
        return ResponseEntity.ok().body(bimesters);
    }

    @PostMapping("/{disciplineId}/{studentId}")
    public ResponseEntity<BimesterGradeResponseDTO> create(@PathVariable Long disciplineId, @PathVariable Long studentId, @RequestBody @Valid BimesterGradeRequestDTO dto, @AuthenticationPrincipal User user) {
        Long idTeacher = user.getId();
        BimesterGrade newBimesterGrade = serviceBimesterGrade.create(disciplineId, dto, idTeacher,  studentId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBimesterGrade.getId())
                .toUri();
        BimesterGradeResponseDTO response = mapperBimesterGradeMapper.toResponseDTO(newBimesterGrade);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{disciplineId}/{studentId}")
    public ResponseEntity<BimesterGradeResponseDTO> update(@PathVariable Long disciplineId, @PathVariable Long studentId, @RequestBody @Valid BimesterGradeRequestDTO dto, @AuthenticationPrincipal User user) {
        Long teacherId = user.getId();
        BimesterGrade current = serviceBimesterGrade.update(disciplineId, dto, studentId, teacherId);
        BimesterGradeResponseDTO response = mapperBimesterGradeMapper.toResponseDTO(current);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{disciplineId}/{studentId}")
    public ResponseEntity<BimesterGradeResponseDTO> updateFinal(@PathVariable Long disciplineId, @PathVariable Long studentId, @RequestBody @Valid BimesterGradeFinalRequestDTO dto, @AuthenticationPrincipal User user){
        Long teacherId = user.getId();
        BimesterGrade current = serviceBimesterGrade.updateFinal(disciplineId, dto, studentId, teacherId);
        BimesterGradeResponseDTO response = mapperBimesterGradeMapper.toResponseDTO(current);
        return ResponseEntity.ok().body(response);
    }

}