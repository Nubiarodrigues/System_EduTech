package com.edutech.backend.utilstest;

import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.utils.ValidatorUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    public void testValidateEmailResponsable_NullOrEmpty(){
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

    @Test
    public void testvalidateTelephone_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateTelephone("83986516605"));
    }

    @Test
    public void testValidateTelephone_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateTelephone(null);
        });
        assertEquals("O telefone não deve ser nulo ou vazio", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateTelephone("");
        });
        assertEquals("O telefone não deve ser nulo ou vazio", e.getMessage());
    }

    @Test
    public void testvalidateTelephone_InvalidFormat(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateTelephone("(83) 9 8651-6605");
        });
        assertEquals("O formato do telefone é inválido", e.getMessage());
    }

    @Test
    public void testValidateCep_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateCep("58067047"));
    }

    @Test
    public void testValidateCep_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCep(null);
        });
        assertEquals("O cep não deve ser nulo ou vazio", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateCep("");
        });
        assertEquals("O cep não deve ser nulo ou vazio", e.getMessage());
    }

    @Test
    public void testValidateCep_InvalidFormat(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateCep("58067-047");
        });
        assertEquals("O formato do CEP é inválido", e.getMessage());
    }

    @Test
    public void testValidateName_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateName("João da Silva"));
    }

    @Test
    public void testValidateName_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName(null);
        });
        assertEquals("O nome não deve ser nulo ou vazio", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName("");
        });
        assertEquals("O nome não deve ser nulo ou vazio", e.getMessage());
    }

    @Test
    public void testValidateName_InvalidFormat_PrepositionUpperCase(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName("Maria Aparecida Da Silva");
        });
        assertEquals("Preposições devem estar em minúsculas.", e.getMessage());
    }

    @Test
    public void testValidateName_InvalidFormat_LetterInitialLowerCase(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName("Maria aparecida da Silva");
        });
        assertEquals("Cada nome deve começar com letra maiúscula.", e.getMessage());
    }

    @Test
    public void testValidateName_TooShort() {
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName("Anna");
        });
        assertEquals("Nome muito curto.", e.getMessage());
    }

    @Test
    public void testValidateName_TooLong(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName("Núbia Maria Rodrigues da Silva Santos Pereira de Almeida Costa Carvalho Fernandes Oliveira Souza Lima Barbosa Marques Dias");
        });
        assertEquals("Nome muito longo.", e.getMessage());
    }

    @Test
    public void testValidateSus_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateSus("703406021173953"));
    }

    @Test
    public void testValidateSus_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateSus(null);
        });
        assertEquals("O número do sus não deve ser nulo ou vazio", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateSus("");
        });
        assertEquals("O número do sus não deve ser nulo ou vazio", e.getMessage());
    }

    @Test
    public void testValidateSus_InvalidFormat(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateSus("7034060211739");
        });
        assertEquals("O número do sus deve conter 15 números.", e.getMessage());
    }

    @Test
    public void testValidateBirthDate_ValidFormat(){
        LocalDate validDate = LocalDate.of(2002, 1, 22);

        assertDoesNotThrow(() -> {
            ValidatorUtils.validateBirthDate(validDate);
        });
    }

    @Test
    public void testValidateBirthDate_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateBirthDate(null);
        });
        assertEquals("A data de nascimento não deve ser nulo", e.getMessage());
    }

    @Test
    public void testValidateBirthDate_InvalidFutureDate(){
        LocalDate futureDate = LocalDate.now().plusDays(1);

        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateBirthDate(futureDate);
        });
        assertEquals("A data de nascimento não pode ser no futuro", e.getMessage());
    }

    @Test
    public void testValidateBirthDate_InvalidDate(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateBirthDate(LocalDate.of(1890,1, 1));
        });
        assertEquals("A data de nascimento é inválida." , e.getMessage());
    }

    @Test
    public void testValidateBirthDate_MinimumAge(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateBirthDate(LocalDate.of(2022, 1,1));
        });
        assertEquals("O usuário precisa ter no mínimo 4 anos de idade.", e.getMessage());
    }
}
