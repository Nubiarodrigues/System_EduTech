package com.edutech.backend.controllers;

import com.edutech.backend.dtos.DisciplineRequestDTO;
import com.edutech.backend.dtos.DisciplineResponseDTO;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.mapper.DisciplineMapper;
import com.edutech.backend.services.DisciplineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService serviceDiscipline;
    private final DisciplineMapper mapperDiscipline;

    @GetMapping
    public ResponseEntity<List<DisciplineResponseDTO>> findAll() {
        List<DisciplineResponseDTO> disciplines = serviceDiscipline.findAll();
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineResponseDTO> findById(@PathVariable Long id) {
        Discipline discipline = serviceDiscipline.findById(id);
        DisciplineResponseDTO response = mapperDiscipline.toResponseDTO(discipline);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<DisciplineResponseDTO> create(@RequestBody @Valid DisciplineRequestDTO dto){
        Discipline newDiscipline = serviceDiscipline.createDiscipline(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDiscipline.getId())
                .toUri();
        DisciplineResponseDTO response = mapperDiscipline.toResponseDTO(newDiscipline);
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineResponseDTO> update(@PathVariable Long id, @RequestBody @Valid DisciplineRequestDTO dto){
        Discipline current = serviceDiscipline.updateDiscipline(id, dto);
        DisciplineResponseDTO response = mapperDiscipline.toResponseDTO(current);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisciplineResponseDTO> delete(@PathVariable Long id){
        serviceDiscipline.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }

}
