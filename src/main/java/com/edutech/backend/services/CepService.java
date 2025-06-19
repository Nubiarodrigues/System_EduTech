package com.edutech.backend.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.edutech.backend.dtos.AdressDTO;
import com.edutech.backend.exceptions.ExternalServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CepService {

	private final OkHttpClient client = new OkHttpClient();

	public String findAdress(String cep) {

		Request request = new Request.Builder().url("https://viacep.com.br/ws/" + cep + "/json").build();

		try (Response response = client.newCall(request).execute()) {

			if (response.isSuccessful()) {
				String json = response.body().string();
				ObjectMapper mapper = new ObjectMapper();
				AdressDTO dto = mapper.readValue(json, AdressDTO.class);
				return formatAdress(dto);
			} else {
				throw new ExternalServiceException("Erro... (status: " + response.code() + ")");
			}

		} catch (IOException e) {
			throw new ExternalServiceException("Falha na requisição");
		}
	}

	private String formatAdress(AdressDTO dto) {
		return String.format("%s, %s - %s/%s - %s", dto.logradouro(), dto.bairro(), dto.localidade(), dto.uf(), dto.cep());
	}

}
