package com.edutech.backend.controllers;

import com.edutech.backend.dtos.schoolnotice.SchoolNoticesRequestDTO;
import com.edutech.backend.dtos.schoolnotice.SchoolNoticesResponseDTO;
import com.edutech.backend.entities.SchoolNotices;
import com.edutech.backend.entities.User;
import com.edutech.backend.mapper.SchoolNoticesMapper;
import com.edutech.backend.services.SchoolNoticesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/warnings")
@RequiredArgsConstructor
public class SchoolNoticesController {

    private final SchoolNoticesService serviceSchoolNotices;
    private final SchoolNoticesMapper mapperSchoolNotices;

    @GetMapping
    public ResponseEntity<List<SchoolNoticesResponseDTO>> findAll(){
        List<SchoolNoticesResponseDTO> warnings = serviceSchoolNotices.findAll();
        return ResponseEntity.ok(warnings);
    }

    @PostMapping("/{idClassroom}")
    public ResponseEntity<SchoolNoticesResponseDTO> create(@PathVariable Long idClassroom, @RequestBody @Valid SchoolNoticesRequestDTO dto, @AuthenticationPrincipal User user){
        String nameAuthor = user.getName();
        SchoolNotices newSchoolNotices = serviceSchoolNotices.create(dto, idClassroom, nameAuthor);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newSchoolNotices.getId())
                .toUri();
        SchoolNoticesResponseDTO response = mapperSchoolNotices.toResponseDTO(newSchoolNotices);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}/{idClassroom}")
    public ResponseEntity<SchoolNoticesResponseDTO> update(@PathVariable Long id, @PathVariable Long idClassroom, @RequestBody @Valid SchoolNoticesRequestDTO dto, @AuthenticationPrincipal User user){
        String nameAuthor = user.getName();
        SchoolNotices current = serviceSchoolNotices.update(id, dto, nameAuthor);
        SchoolNoticesResponseDTO response = mapperSchoolNotices.toResponseDTO(current);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User user){
        String nameAuthor = user.getName();
        serviceSchoolNotices.delete(id, nameAuthor);
        return ResponseEntity.noContent().build();
    }
}
