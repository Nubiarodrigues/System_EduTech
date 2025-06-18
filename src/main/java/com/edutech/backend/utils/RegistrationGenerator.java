package com.edutech.backend.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RegistrationGenerator {
	
	private Set<String> generatedRecords = new HashSet<>();
	private Random random = new Random();

	public String generateRegistrationUnique(String registration) {
	    do {
	        int number = random.nextInt(1_000_000);
	        registration = String.format("%06d", number);
	    } while (generatedRecords.contains(registration));
	    
	    generatedRecords.add(registration);
	    return registration;
	}

}
