package com.edutech.backend.controllers;

import com.edutech.backend.dtos.ClassesTaughtRequestDTO;
import com.edutech.backend.dtos.ClassesTaughtResponseDTO;
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
        ClassesTaught newClassesTaught = serviceClassesTaught.createRegister(disciplineId, dto, user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newClassesTaught.getId())
                .toUri();
        ClassesTaughtResponseDTO response = mapperClassesTaught.toResponseDTO(newClassesTaught);
        return ResponseEntity.created(location).body(response);
    }


}
