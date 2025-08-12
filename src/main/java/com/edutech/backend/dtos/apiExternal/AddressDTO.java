package com.edutech.backend.dtos.apiExternal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDTO(
        @NotNull String logradouro,
        @NotNull String bairro,
        @NotNull String localidade,
        @NotNull String complemento,
        @NotNull String numero,
        @NotNull String uf,
        @NotNull String cep
) {
}
