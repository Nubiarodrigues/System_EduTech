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
	

	public static void validateCpf(String cpf) {

		if (cpf == null || cpf.isEmpty()) {
			throw new InvalidDataException("O CPF não deve ser nulo ou vazio");
		}

		if (!cpf.matches("^\\d{11}$")) {
			throw new InvalidDataException("Formato do CPF é inválido");
		}

		int[] cpf_digitos = new int[cpf.length()];
		boolean result = false;

		for (int i = 0; i < 11; i++) {
			cpf_digitos[i] = Character.getNumericValue(cpf.charAt(i));
		}

		result = checkCpf(cpf_digitos);

		if (!result == true) {
			throw new InvalidDataException("CPF inválido");
		}

	}
	

	private static boolean checkCpf(int[] numberCpf) {
		int i, j, sum, module, dv1, dv2;

		// #1
		sum = 0;
		j = 10;

		for (i = 0; i < 9; i++) {
			sum += numberCpf[i] * j;
			j -= 1;
		}

		module = sum % 11;

		if (module < 2) {
			dv1 = 0;
		} else {
			dv1 = 11 - module;
		}

		// #2
		sum = 0;
		j = 11;
		for (i = 0; i < 10; i++) {
			sum += numberCpf[i] * j;
			j -= 1;
		}

		module = sum % 11;
		if (module < 2) {
			dv2 = 0;
		} else {
			dv2 = 11 - module;
		}

		return (numberCpf[9] == dv1) && (numberCpf[10] == dv2);

	}

	
	public static void validateTelephone(String telephone) {

		if (telephone == null || telephone.isEmpty()) {
			throw new InvalidDataException("O telefone não deve ser nulo ou vazio");
		}

		if (!telephone.matches("^\\(?\\d{2}\\)?\\s?9?\\s?\\d{4}-?\\d{4}$")) {
			throw new InvalidDataException("Formato do telefone é inválido");
		}

		String digitsOnly = telephone.replaceAll("\\D", "");
		
		if (digitsOnly.charAt(2) == '9') {
			System.out.println("Ok");
		} else {
			throw new InvalidDataException("Coloque o 9 depois do DDD.");
		}
	}
}
