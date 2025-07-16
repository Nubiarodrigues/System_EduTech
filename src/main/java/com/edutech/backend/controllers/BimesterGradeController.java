package com.edutech.backend.controllers;

import com.edutech.backend.dtos.bimestergrade.BimesterGradeRequestDTO;
import com.edutech.backend.dtos.bimestergrade.BimesterGradeResponseDTO;
import com.edutech.backend.entities.BimesterGrade;
import com.edutech.backend.entities.User;
import com.edutech.backend.mapper.BimesterGradeMapper;
import com.edutech.backend.services.BimesterGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bimestre-grade")
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
    public ResponseEntity<BimesterGradeResponseDTO> create(@PathVariable Long disciplineId, @RequestBody BimesterGradeRequestDTO dto, @AuthenticationPrincipal User user, @PathVariable Long studentId) {
        Long idTeacher = user.getId();
        BimesterGrade newBimesterGrade = serviceBimesterGrade.create(disciplineId, dto, idTeacher,  studentId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBimesterGrade.getId())
                .toUri();
        BimesterGradeResponseDTO response = mapperBimesterGradeMapper.toResponseDTO(newBimesterGrade);
        return ResponseEntity.created(location).body(response);
    }

}
