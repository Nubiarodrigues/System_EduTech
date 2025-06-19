package com.edutech.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AdressDTO(String logradouro, String bairro, String localidade, String uf, String cep) {

}
