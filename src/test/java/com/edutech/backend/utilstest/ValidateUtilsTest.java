package com.edutech.backend.utilstest;

import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.utils.ValidatorUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateUtilsTest {

    @Test
    public void testValidateEmail_ValidFormat(){
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

    @Test
    public void testValidateEmailResponsable_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateEmailResponsable("joao.silvadasilva@gmail.com"));
    }

    @Test
    public void testValidateEmailResponsable_NullIOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmailResponsable(null);
        });
        assertEquals("O e-mail não deve ser nulo ou vazio", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateEmailResponsable("");
        });
        assertEquals("O e-mail não deve ser nulo ou vazio", e.getMessage());
    }

    @Test
    public void testValidateEmailResponsable_InvalidFormat() {
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateEmailResponsable("joaosilva!234@gmail.com");
        });
        assertEquals("O formato do e-mail do responsável inválido.", e.getMessage());
    }

    @Test
    public void testValidateCpf_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateCpf("08652105430"));
    }

    @Test
    public void testValidateCpf_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCpf(null);
        });
        assertEquals("O CPF não deve ser nulo ou vazio", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCpf("");
        });
        assertEquals("O CPF não deve ser nulo ou vazio", e.getMessage());
    }

    @Test
    public void testvalidateCpf_InvalidFormat(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateCpf("086521054");
        });
        assertEquals("O formato do CPF é inválido", e.getMessage());
    }

    @Test
    public void testvalidateCpfValid(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCpf("45655555596");
        });
        assertEquals("CPF inválido", e.getMessage());
    }


}
