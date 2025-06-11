package com.edutech.backend.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Student;
import com.edutech.backend.enuns.Shift;
import com.edutech.backend.enuns.TeachingState;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.StudentRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	private final ClassroomRepository repositoryClassroom;
	private final StudentRepository repositoryStudent;

	public TestConfig(ClassroomRepository repositoryClassroom, StudentRepository repositoryStudent) {
		this.repositoryClassroom = repositoryClassroom;
		this.repositoryStudent = repositoryStudent;
	}

	@Override
	public void run(String... args) throws Exception {

		Classroom c1 = new Classroom(1, "A", 35, Shift.MANHA, 2025, TeachingState.ENSINO_MEDIO);
		Classroom c2 = new Classroom(2, "A", 35, Shift.MANHA, 2025, TeachingState.ENSINO_MEDIO);
		Classroom c3 = new Classroom(3, "A", 35, Shift.MANHA, 2025, TeachingState.ENSINO_MEDIO);

		repositoryClassroom.saveAll(Arrays.asList(c1, c2, c3));

		Student s1 = new Student("João da Silva", LocalDate.parse("2003-01-20"), "256.222.222-33", "2556874122235", "Rua A, 123", "839555-0001", "joao.resp@gmail.com", "Maria Aparecida", "José da Silva", c1);
		Student s2 = new Student("Ana Souza", LocalDate.parse("2004-02-10"), "123.456.789-00", "1234567890123", "Rua B, 456", "839555-0002", "ana.resp@gmail.com", "Luciana Souza", "Carlos Souza", c1);
		Student s3 = new Student("Pedro Lima", LocalDate.parse("2005-03-15"), "987.654.321-00", "9876543210987", "Rua C, 789", "839555-0003", "pedro.resp@gmail.com", "Patrícia Lima", "Roberto Lima", c1);

		Student s4 = new Student("Mariana Alves", LocalDate.parse("2003-05-20"), "321.654.987-00", "3216549871234", "Rua D, 321", "839555-0004", "mariana.resp@gmail.com", "Eliane Alves", "Marcelo Alves", c2);
		Student s5 = new Student("Lucas Rocha", LocalDate.parse("2004-06-25"), "111.222.333-44", "1122334455667", "Rua E, 654", "839555-0005", "lucas.resp@gmail.com", "Fernanda Rocha", "Tiago Rocha", c2);
		Student s6 = new Student("Bruna Costa", LocalDate.parse("2005-07-30"), "444.555.666-77", "9988776655443", "Rua F, 987", "839555-0006", "bruna.resp@gmail.com", "Sueli Costa", "Jorge Costa", c2);

		Student s7 = new Student("Carlos Mendes", LocalDate.parse("2003-09-10"), "777.888.999-00", "7778889990000", "Rua G, 159", "839555-0007", "carlos.resp@gmail.com", "Juliana Mendes", "Eduardo Mendes", c3);
		Student s8 = new Student("Larissa Silva", LocalDate.parse("2004-10-15"), "555.444.333-22", "5554443332211", "Rua H, 753", "839555-0008", "larissa.resp@gmail.com", "Denise Silva", "Luiz Silva", c3);
		Student s9 = new Student("Thiago Martins", LocalDate.parse("2005-11-20"), "999.000.111-22", "9990001112233", "Rua I, 357", "839555-0009", "thiago.resp@gmail.com", "Cláudia Martins", "Paulo Martins", c3);
		
		repositoryStudent.saveAll(Arrays.asList(s1,s2,s3,s4,s5,s6,s7,s8,s9));
	}

}
