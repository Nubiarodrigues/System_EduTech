package com.edutech.backend.controllers;

import com.edutech.backend.dtos.frequency.FrequencyRequestDTO;
import com.edutech.backend.dtos.frequency.FrequencyResponseDTO;
import com.edutech.backend.entities.Frequency;
import com.edutech.backend.mapper.FrequencyMapper;
import com.edutech.backend.services.FrequencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/frequencies")
@RequiredArgsConstructor
public class FrequencyController {

    private final FrequencyService serviceFrequency;
    private final FrequencyMapper mapperFrequency;

    @PostMapping("/{idStudent}/{idDiscipline}")
    public ResponseEntity<FrequencyResponseDTO> create(@PathVariable Long idStudent, @PathVariable Long idDiscipline, @RequestBody @Valid FrequencyRequestDTO dto) {
        Frequency newFrequency = serviceFrequency.create(dto, idStudent, idDiscipline);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newFrequency.getId())
                .toUri();
        FrequencyResponseDTO response = mapperFrequency.toResponse(newFrequency);
        return ResponseEntity.created(location).body(response);
    }
}
