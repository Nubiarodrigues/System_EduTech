package com.edutech.backend.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RegistrationGenerator {
	
	private Set<String> generatedRecordsStudent = new HashSet<>();
	private Set<String> generatedRecordsTeacher = new HashSet<>();
	private Set<String> generatedRecordsCoordinator = new HashSet<>();
	private Set<String> generatedRecordsStudentOperatorAndAdmin = new HashSet<>();
	private Random random = new Random();

	public String generateRegistrationUniqueStudent(String registration) {
		String prefix = "4";

		do {
			int number = random.nextInt(100_000);
			String numberStr = String.format("%05d", number);
			registration = prefix + numberStr;
		} while (generatedRecordsStudent.contains(registration));

		generatedRecordsStudent.add(registration);
		return registration;
	}

	public String generateRegistrationUniqueTeacher(String registration) {
		String prefix = "6";

		do {
			int number = random.nextInt(100_000);
			String numberStr = String.format("%05d", number);
			registration = prefix + numberStr;
		} while (generatedRecordsTeacher.contains(registration));

		generatedRecordsTeacher.add(registration);
		return registration;
	}

	public String generateRegistrationUniqueCoordinator(String registration) {
		String prefix = "7";

		do {
			int number = random.nextInt(100_000);
			String numberStr = String.format("%05d", number);
			registration = prefix + numberStr;
		} while (generatedRecordsCoordinator.contains(registration));

		generatedRecordsCoordinator.add(registration);
		return registration;
	}

	public String generateRegistrationUniqueOperatorAndAdmin(String registration) {
		String prefix = "9";

		do {
			int number = random.nextInt(100_000);
			String numberStr = String.format("%05d", number);
			registration = prefix + numberStr;
		} while (generatedRecordsStudentOperatorAndAdmin.contains(registration));

		generatedRecordsStudentOperatorAndAdmin.add(registration);
		return registration;
	}
}