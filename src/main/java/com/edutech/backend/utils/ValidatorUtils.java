package com.edutech.backend.utils;

import com.edutech.backend.exceptions.InvalidDataException;

import java.time.LocalDate;
import java.util.List;

public class ValidatorUtils {

	public static void validateEmail(String email) {

		if (email == null || email.isEmpty()) {
			throw new InvalidDataException("O campo de e-mail é obrigatório.");
		}

		if (!email.matches("^[a-zA-Z0-9.]+@[a-zA-Z]+\\.(pb)\\.gov\\.br$")) {
            throw new InvalidDataException("O formato do e-mail é inválido.");
        }
	}
	
	public static void validateEmailResponsable(String email) {
		if (email == null || email.isEmpty()) {
			throw new InvalidDataException("O campo de e-mail do responsável é obrigatório.");
		}
		
		if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			throw new InvalidDataException("Formato inválido: verifique o e-mail do responsável.");
		}
		
	}

	public static void validateCpf(String cpf) {

		if (cpf == null || cpf.isEmpty()) {
			throw new InvalidDataException("O campo de CPF é obrigatório.");
		}

		if (!cpf.matches("^\\d{11}$")) {
			throw new InvalidDataException("Formato de CPF inválido. O valor deve conter exatamente 11 dígitos numéricos.");
		}

		int[] cpf_digitos = new int[cpf.length()];
		boolean result = false;

		for (int i = 0; i < 11; i++) {
			cpf_digitos[i] = Character.getNumericValue(cpf.charAt(i));
		}

		result = checkCpf(cpf_digitos);

		if (!result) {
			throw new InvalidDataException("CPF inválido. O número informado não passou na verificação dos dígitos e não é considerado um CPF válido.");
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
			throw new InvalidDataException("O campo de telefone é obrigatório.");
		}

		if (!telephone.matches("^\\d{11}$")) {
			throw new InvalidDataException("Formato do telefone inválido. O valor deve conter exatamente 11 dígitos numéricos.");
		}
	}

	public static void validateCep(String cep) {

		if (cep == null || cep.isEmpty()) {
			throw new InvalidDataException("O campo de CEP é obrigatório.");
		}

		if (!cep.matches("^\\d{8}$")) {
			throw new InvalidDataException("Formato do CEP inválido. O valor deve conter exatamente 8 dígitos numéricos.");
		}
	}

	public static void validateName(String name) {

		if (name == null || name.isEmpty()) {
			throw new InvalidDataException("O campo de nome é obrigatório.");
		}
		if (name.length() <= 5) {
			throw new InvalidDataException("O nome informado é muito curto. Deve conter no mínimo 6 caracteres.");
		}
		if (name.length() > 100) {
			throw new InvalidDataException("O nome informado é muito longo. Deve conter no máximo 100 caracteres.");
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
			throw new InvalidDataException("O campo de SUS é obrigatório.");
		}

		if (!sus.matches("^\\d{15}$")) {
			throw new InvalidDataException("Formato do SUS inválido. O valor deve conter exatamente 15 dígitos numéricos.");
		}

	}
	
	public static void validateBirthDate(LocalDate birthDate) {

		if (birthDate == null) {
			throw new InvalidDataException("O campo de data de nascimento é obrigatório.");
		}
		
		LocalDate today = LocalDate.now();
		LocalDate minDate = today.minusYears(4);
		
		if(birthDate.isAfter(today)) {
			throw new InvalidDataException("Data de nascimento inválida: não pode ser uma data futura.");
		}
		
		if(birthDate.isBefore(today.minusYears(120))) {
			throw new InvalidDataException("Data de nascimento inválida: não pode ser anterior a 120 anos a partir da data atual.");
		}
		
		if(birthDate.isAfter(minDate)) {
			throw new InvalidDataException("A idade mínima permitida para o usuário é 4 anos.");
		}
	}

}