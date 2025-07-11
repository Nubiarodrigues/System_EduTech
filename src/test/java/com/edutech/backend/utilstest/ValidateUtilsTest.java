package com.edutech.backend.utilstest;

import com.edutech.backend.exceptions.InvalidDataException;
import com.edutech.backend.utils.ValidatorUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateUtilsTest {

    // -------- EMAIL TESTS --------
    @Test
    public void testValidateEmail_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateEmail("joao.silva@aluno.pb.gov.br"));
    }

    @Test
    public void testValidateEmail_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmail(null);
        });
        assertEquals("O campo de e-mail é obrigatório.", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmail("");
        });
        assertEquals("O campo de e-mail é obrigatório.", e.getMessage());
    }

    @Test
    public void testValidateEmail_InvalidFormat() {
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmail("joaosilva@gmail.com");
        });
        assertEquals("O formato do e-mail é inválido.", e.getMessage());
    }

    @Test
    public void testValidateEmail_InvalidFormatWithSpaces() {
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmail(" joao.silva@aluno.pb.gov.br");
        });
        assertEquals("O formato do e-mail é inválido.", e.getMessage());
    }


    // -------- EMAIL_RESPONSABLE TESTS --------
    @Test
    public void testValidateEmailResponsable_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateEmailResponsable("joao.silvadasilva@gmail.com"));
    }

    @Test
    public void testValidateEmailResponsable_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmailResponsable(null);
        });
        assertEquals("O campo de e-mail do responsável é obrigatório.", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateEmailResponsable("");
        });
        assertEquals("O campo de e-mail do responsável é obrigatório.", e.getMessage());
    }

    @Test
    public void testValidateEmailResponsable_InvalidFormat() {
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateEmailResponsable("joaosilva!234@gmail.com");
        });
        assertEquals("Formato inválido: verifique o e-mail do responsável.", e.getMessage());
    }

    @Test
    public void testValidateEmailResponsable_InvalidFormatWithSpaces() {
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateEmailResponsable("  joao.silvadasilva@gmail.com");
        });
        assertEquals("Formato inválido: verifique o e-mail do responsável.", e.getMessage());
    }

    // -------- CPF TESTS --------
    @Test
    public void testValidateCpf_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateCpf("08652105430"));
    }

    @Test
    public void testValidateCpf_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCpf(null);
        });
        assertEquals("O campo de CPF é obrigatório.", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCpf("");
        });
        assertEquals("O campo de CPF é obrigatório.", e.getMessage());
    }

    @Test
    public void testValidateCpf_InvalidFormat(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateCpf("086521054");
        });
        assertEquals("Formato de CPF inválido. O valor deve conter exatamente 11 dígitos numéricos.", e.getMessage());
    }

    @Test
    public void testValidateCpf_InvalidFormatNumbersAndLetters(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCpf("086gbh0g589");
        });
        assertEquals("Formato de CPF inválido. O valor deve conter exatamente 11 dígitos numéricos.", e.getMessage());
    }

    @Test
    public void testValidateCpf_InvalidCheckDigit(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCpf("45655555596");
        });
        assertEquals("CPF inválido. O número informado não passou na verificação dos dígitos e não é considerado um CPF válido.", e.getMessage());
    }

    // -------- TELEPHONE TESTS --------
    @Test
    public void testValidateTelephone_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateTelephone("83986516605"));
    }

    @Test
    public void testValidateTelephone_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateTelephone(null);
        });
        assertEquals("O campo de telefone é obrigatório.", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateTelephone("");
        });
        assertEquals("O campo de telefone é obrigatório.", e.getMessage());
    }

    @Test
    public void testValidateTelephone_InvalidFormat(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateTelephone("(83) 9 8651-6605");
        });
        assertEquals("Formato do telefone inválido. O valor deve conter exatamente 11 dígitos numéricos.", e.getMessage());
    }

    // -------- CEP TESTS --------
    @Test
    public void testValidateCep_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateCep("58067047"));
    }

    @Test
    public void testValidateCep_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateCep(null);
        });
        assertEquals("O campo de CEP é obrigatório.", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateCep("");
        });
        assertEquals("O campo de CEP é obrigatório.", e.getMessage());
    }

    @Test
    public void testValidateCep_InvalidFormat(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
           ValidatorUtils.validateCep("58067-047");
        });
        assertEquals("Formato do CEP inválido. O valor deve conter exatamente 8 dígitos numéricos.", e.getMessage());
    }

    // -------- NAME TESTS --------
    @Test
    public void testValidateName_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateName("João da Silva"));
    }

    @Test
    public void testValidateName_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName(null);
        });
        assertEquals("O campo de nome é obrigatório.", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName("");
        });
        assertEquals("O campo de nome é obrigatório.", e.getMessage());
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
            ValidatorUtils.validateName("Ana");
        });
        assertEquals("O nome informado é muito curto. Deve conter no mínimo 6 caracteres.", e.getMessage());
    }

    @Test
    public void testValidateName_TooLong(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateName("Núbia Maria Rodrigues da Silva Santos Pereira de Almeida Costa Carvalho Fernandes Oliveira Souza Lima Barbosa Marques Dias");
        });
        assertEquals("O nome informado é muito longo. Deve conter no máximo 100 caracteres.", e.getMessage());
    }

    // -------- SUS TESTS --------
    @Test
    public void testValidateSus_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateSus("703406021173953"));
    }

    @Test
    public void testValidateSus_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateSus(null);
        });
        assertEquals("O campo de SUS é obrigatório.", e.getMessage());

        e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateSus("");
        });
        assertEquals("O campo de SUS é obrigatório.", e.getMessage());
    }

    @Test
    public void testValidateSus_InvalidFormat(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateSus("7034060211739");
        });
        assertEquals("Formato do SUS inválido. O valor deve conter exatamente 15 dígitos numéricos.", e.getMessage());
    }

    // -------- BIRTH DATE TESTS --------
    @Test
    public void testValidateBirthDate_ValidFormat(){
        assertDoesNotThrow(() -> ValidatorUtils.validateBirthDate(LocalDate.of(2002, 1, 22)));
    }

    @Test
    public void testValidateBirthDate_NullOrEmpty(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateBirthDate(null);
        });
        assertEquals("O campo de data de nascimento é obrigatório.", e.getMessage());
    }

    @Test
    public void testValidateBirthDate_InvalidFutureDate(){
        LocalDate futureDate = LocalDate.now().plusDays(1);

        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateBirthDate(futureDate);
        });
        assertEquals("Data de nascimento inválida: não pode ser uma data futura.", e.getMessage());
    }

    @Test
    public void testValidateBirthDate_InvalidDate(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateBirthDate(LocalDate.of(1890,1, 1));
        });
        assertEquals("Data de nascimento inválida: não pode ser anterior a 120 anos a partir da data atual." , e.getMessage());
    }

    @Test
    public void testValidateBirthDate_MinimumAge(){
        InvalidDataException e = assertThrows(InvalidDataException.class, () -> {
            ValidatorUtils.validateBirthDate(LocalDate.of(2022, 1,1));
        });
        assertEquals("A idade mínima permitida para o usuário é 4 anos.", e.getMessage());
    }
}
