package com.edutech.backend.utilstest;

import com.edutech.backend.utils.RegistrationGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationGeneratorTest {

    private void testUniqueRegistrations(Function<Void, String> generatorMethod) {
        Set<String> generated = new HashSet<>();

        for (int i = 0; i < 300; i++) {
            String reg = generatorMethod.apply(null);
            assertEquals(6, reg.length());
            assertTrue(reg.matches("\\d{6}"), "Registro com formato inválido: " + reg);
            assertFalse(generated.contains(reg), "Matrícula repetida: " + reg);
            generated.add(reg);
        }
    }

    @Test
    public void testGenerateRegistrationUniqueStudent(){
        RegistrationGenerator generator = new RegistrationGenerator();
        testUniqueRegistrations(v -> generator.generateRegistrationUniqueStudent(null));
    }

    @Test
    public void testGenerateRegistrationUniqueTeacher(){
        RegistrationGenerator generator = new RegistrationGenerator();
        testUniqueRegistrations(v -> generator.generateRegistrationUniqueTeacher(null));
    }

    @Test
    public void testGenerateRegistrationUniqueCoordinator(){
        RegistrationGenerator generator = new RegistrationGenerator();
        testUniqueRegistrations(v -> generator.generateRegistrationUniqueCoordinator(null));
    }

    @Test
    public void testGenerateRegistrationUniqueOperatorAndAdmin(){
        RegistrationGenerator generator = new RegistrationGenerator();
        testUniqueRegistrations(v -> generator.generateRegistrationUniqueOperatorAndAdmin(null));
    }
}
