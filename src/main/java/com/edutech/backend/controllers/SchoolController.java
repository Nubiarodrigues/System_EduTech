package com.edutech.backend.controllers;

import com.edutech.backend.dtos.school.SchoolRequestDTO;
import com.edutech.backend.dtos.school.SchoolRequestSimpleDTO;
import com.edutech.backend.dtos.school.SchoolResponseDTO;
import com.edutech.backend.entities.School;
import com.edutech.backend.mapper.SchoolMapper;
import com.edutech.backend.services.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService serviceSchool;
    private final SchoolMapper mapperSchool;

    @GetMapping("/{id}")
    public ResponseEntity<SchoolResponseDTO> findById(@PathVariable Long id){
        School school = serviceSchool.findById(id);
        return ResponseEntity.ok(mapperSchool.toResponseDTO(school));
    }

    @PostMapping
    public ResponseEntity<SchoolResponseDTO> create(@RequestBody @Valid SchoolRequestDTO dto){
        School newSchool = serviceSchool.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newSchool.getId())
                .toUri();
        SchoolResponseDTO response = mapperSchool.toResponseDTO(newSchool);
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SchoolResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SchoolRequestSimpleDTO dto){
        School current = serviceSchool.updatePartial(id, dto);
        SchoolResponseDTO response = mapperSchool.toResponseDTO(current);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        serviceSchool.delete(id);
        return ResponseEntity.noContent().build();
    }
}
