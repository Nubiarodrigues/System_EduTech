package com.edutech.backend.controllers;

import com.edutech.backend.dtos.classestaught.ClassesTaughtRequestDTO;
import com.edutech.backend.dtos.classestaught.ClassesTaughtResponseDTO;
import com.edutech.backend.entities.ClassesTaught;
import com.edutech.backend.entities.User;
import com.edutech.backend.mapper.ClassesTaughtMapper;
import com.edutech.backend.services.ClassesTaughtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/register-classes")
@RequiredArgsConstructor
public class ClassesTaughtController {

    private final ClassesTaughtService serviceClassesTaught;
    private final ClassesTaughtMapper mapperClassesTaught;


    @GetMapping
    public ResponseEntity<List<ClassesTaughtResponseDTO>> findAll(){
        List<ClassesTaughtResponseDTO> recordsClasses = serviceClassesTaught.findAll();
        return ResponseEntity.ok().body(recordsClasses);
    }

    @PostMapping("{disciplineId}")
    public ResponseEntity<ClassesTaughtResponseDTO> create(@PathVariable Long disciplineId, @RequestBody @Valid ClassesTaughtRequestDTO dto, @AuthenticationPrincipal User user) {
        Long teacherId = user.getId();
        ClassesTaught newClassesTaught = serviceClassesTaught.createRegister(disciplineId, dto, teacherId);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newClassesTaught.getId())
                .toUri();
        ClassesTaughtResponseDTO response = mapperClassesTaught.toResponseDTO(newClassesTaught);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassesTaughtResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ClassesTaughtRequestDTO dto, @AuthenticationPrincipal User user) {
        Long teacherId = user.getId();
        ClassesTaught current = serviceClassesTaught.updateRegister(id, dto, teacherId);
        ClassesTaughtResponseDTO response = mapperClassesTaught.toResponseDTO(current);
        return ResponseEntity.ok().body(response);
    }
}
