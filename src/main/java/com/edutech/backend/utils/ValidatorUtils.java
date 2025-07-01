package com.edutech.backend.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import com.edutech.backend.exceptions.InvalidDataException;

public class ValidatorUtils {

	public static void validateEmail(String email) {

		if (email == null || email.isEmpty()) {
			throw new InvalidDataException("O e-mail não deve ser nulo ou vazio");
		}

		if (!email.matches("^[a-zA-Z0-9.]+@[a-zA-Z]+\\.(pb)\\.gov\\.br$")) {
            throw new InvalidDataException("O formato do e-mail inválido");
        }
	}
	
	public static void validateEmailResponsable(String email) {
		if (email == null || email.isEmpty()) {
			throw new InvalidDataException("O e-mail não deve ser nulo ou vazio");
		}
		
		if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			throw new InvalidDataException("O formato do e-mail do responsável inválido.");
		}
		
	}

	public static void validateCpf(String cpf) {

		if (cpf == null || cpf.isEmpty()) {
			throw new InvalidDataException("O CPF não deve ser nulo ou vazio");
		}

		if (!cpf.matches("^\\d{11}$")) {
			throw new InvalidDataException("O formato do CPF é inválido");
		}

		int[] cpf_digitos = new int[cpf.length()];
		boolean result = false;

		for (int i = 0; i < 11; i++) {
			cpf_digitos[i] = Character.getNumericValue(cpf.charAt(i));
		}

		result = checkCpf(cpf_digitos);

		if (!result) {
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

		if (!telephone.matches("^\\d{11}$")) {
			throw new InvalidDataException("O formato do telefone é inválido");
		}
	}

	public static void validateCep(String cep) {

		if (cep == null || cep.isEmpty()) {
			throw new InvalidDataException("O cep não deve ser nulo ou vazio");
		}

		if (!cep.matches("^\\d{8}$")) {
			throw new InvalidDataException("O formato do CEP é inválido");
		}
	}

	public static void validateName(String name) {

		if (name == null || name.isEmpty()) {
			throw new InvalidDataException("O nome não deve ser nulo ou vazio");
		}
		if (name.length() <= 5) {
			throw new InvalidDataException("Nome muito curto.");
		}
		if (name.length() > 100) {
			throw new InvalidDataException("Nome muito longo.");
		}

		// lista de preposições permitidas
		List<String> prepositions = List.of("da", "de", "do", "das", "dos");

		// divide o nome em palavras, removendo espaços extras
		String[] words = name.trim().split("\\s+");

		for(String word : words){
			// se for preposição, deve estar em maiúsculo
			if(prepositions.contains(word.toLowerCase())){
				if(!word.equals(word.toLowerCase())){
					throw new InvalidDataException("Preposições devem estar em minúsculas.");
				}
			} else {
				// se não for preposição, deve começar com letra maiúscula
				if(!Character.isUpperCase(word.charAt(0))){
					throw new InvalidDataException("Cada nome deve começar com letra maiúscula.");
				}
			}
		}
	}

	public static void validateSus(String sus) {

		if (sus == null || sus.isEmpty()) {
			throw new InvalidDataException("O número do sus não deve ser nulo ou vazio");
		}

		if (!sus.matches("^\\d{15}$")) {
			throw new InvalidDataException("O número do sus deve conter 15 números.");
		}

	}
	
	public static void validateBirthDate(LocalDate birthDate) {

		if (birthDate == null) {
			throw new InvalidDataException("A data de nascimento não deve ser nulo");
		}
		
		LocalDate today = LocalDate.now();
		LocalDate minDate = today.minusYears(4);
		
		if(birthDate.isAfter(today)) {
			throw new InvalidDataException("A data de nascimento não pode ser no futuro.");
		}
		
		if(birthDate.isBefore(today.minusYears(120))) {
			throw new InvalidDataException("A data de nascimento é inválida.");
		}
		
		if(birthDate.isAfter(minDate)) {
			throw new InvalidDataException("O usuário precisa ter no mínimo 4 anos de idade.");
		}
	}

}
