package com.edutech.backend.utils;

import com.edutech.backend.exceptions.InvalidDataException;

public class ValidatorUtils {

	public static void validateEmail(String email) {

		if (email == null || email.isEmpty()) {
			throw new InvalidDataException("O e-mail não deve ser nulo ou vazio");
		}

		if (!email.matches("^[a-zA-Z0-9.]+@[a-zA-Z]+\\.(pb)\\.gov\\.br$")) {
			throw new InvalidDataException("Formato de e-mail inválido");
		}
	}

}
