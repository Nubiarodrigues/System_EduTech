package com.edutech.backend.services;

import com.edutech.backend.dtos.apiExternal.AddressDTO;
import com.edutech.backend.exceptions.ExternalServiceException;
import com.edutech.backend.exceptions.InvalidDataException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CepService {

	private final OkHttpClient client;
	private final ObjectMapper mapper;

	public AddressDTO findAddress(String cep) {

		Request request = new Request.Builder().url("https://viacep.com.br/ws/" + cep + "/json").build();

		try (Response response = client.newCall(request).execute()) {

			if (response.isSuccessful()) {
				String json = response.body().string();
				AddressDTO dto = mapper.readValue(json, AddressDTO.class);
				return dto;
			} else {
				throw new InvalidDataException("Formato do cep incorreto, verifique o campo.  Status:  " + response.code());
			}

		} catch (IOException e) {
			throw new ExternalServiceException("Falha na requisição");
		}
	}
}
