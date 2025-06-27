package com.edutech.backend.utilstest;

import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.utils.ValidatorUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateUtilsTest {

    @Test
    public void testValidateEmail_Valid(){
        assertDoesNotThrow(() -> ValidatorUtils.validateEmail("joao.silva@aluno.pb.gov.br"));
    }

    @Test
    public void testValidateEmail_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmail(null);
        });
        assertEquals("O e-mail não deve ser nulo ou vazio", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmail("");
        });
        assertEquals("O e-mail não deve ser nulo ou vazio", e.getMessage());
    }

    @Test
    public void testValidateEmail_InvalidFormat() {
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmail("joaosilva@gmail.com");
        });
        assertEquals("O formato do e-mail inválido", e.getMessage());
    }

}
