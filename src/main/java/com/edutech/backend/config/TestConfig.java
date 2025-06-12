package com.edutech.backend.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Student;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.enuns.Shift;
import com.edutech.backend.enuns.Situation;
import com.edutech.backend.enuns.TeachingState;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.StudentRepository;
import com.edutech.backend.repositories.TeacherRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	private final ClassroomRepository repositoryClassroom;
	private final StudentRepository repositoryStudent;
	private final TeacherRepository repositoryTeacher;

	public TestConfig(ClassroomRepository repositoryClassroom, StudentRepository repositoryStudent, TeacherRepository repositoryTeacher) {
		this.repositoryClassroom = repositoryClassroom;
		this.repositoryStudent = repositoryStudent;
		this.repositoryTeacher = repositoryTeacher;
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
		
		Teacher t1 = Teacher.builder()
				.name("José da Silva Morais")
				.cpf("174.524.556-55")
				.telephone("83598542145")
				.status(Situation.ATIVO)
				.dateBirth(LocalDate.parse("2002-01-22"))
				.workloadTotal(30)
				.rg("222222")
				.address("Rua Maria das Graças, 81 - João Pessoa/PB")
				.matriculation("145876")
				.formedCourse("Licenciatura em Língua Portuguesa")
				.build();

			Teacher t2 = Teacher.builder()
				.name("Ana Carolina Ferreira")
				.cpf("123.456.789-00")
				.telephone("83999887766")
				.status(Situation.ATIVO)
				.dateBirth(LocalDate.parse("1985-04-10"))
				.workloadTotal(40)
				.rg("123123")
				.address("Av. Epitácio Pessoa, 1000 - João Pessoa/PB")
				.matriculation("145877")
				.formedCourse("Licenciatura em Matemática")
				.build();

			Teacher t3 = Teacher.builder()
				.name("Marcos Paulo de Almeida")
				.cpf("321.654.987-11")
				.telephone("83996543210")
				.status(Situation.ATIVO)
				.dateBirth(LocalDate.parse("1990-09-15"))
				.workloadTotal(20)
				.rg("456456")
				.address("Rua das Acácias, 345 - Campina Grande/PB")
				.matriculation("145878")
				.formedCourse("Licenciatura em História")
				.build();

			Teacher t4 = Teacher.builder()
				.name("Fernanda Lopes Andrade")
				.cpf("987.654.321-99")
				.telephone("83991234567")
				.status(Situation.ATIVO)
				.dateBirth(LocalDate.parse("1995-12-05"))
				.workloadTotal(25)
				.rg("789789")
				.address("Rua do Sol, 789 - Sousa/PB")
				.matriculation("145879")
				.formedCourse("Licenciatura em Ciências Biológicas")
				.build();
			
			repositoryTeacher.saveAll(Arrays.asList(t1,t2,t3,t4));
	}

}
