package com.edutech.backend.dtos.apiExternal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AdressDTO(
        String logradouro,
        String bairro,
        String localidade,
        String complemento,
        String numero,
        String uf,
        String cep
) {
}
