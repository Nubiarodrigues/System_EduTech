package com.edutech.backend.controllers;

import com.edutech.backend.dtos.apiExternal.AddressDTO;
import com.edutech.backend.services.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
@RequiredArgsConstructor
public class CepController {

    private final CepService serviceCep;

    @GetMapping("/{cep}")
    public ResponseEntity<AddressDTO> findAddress(@PathVariable String cep) {
        AddressDTO dto = serviceCep.findAddress(cep);
        return ResponseEntity.ok(dto);
    }
}
