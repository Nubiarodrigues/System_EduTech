package com.edutech.backend.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.entities.Student;
import com.edutech.backend.entities.Teacher;
import com.edutech.backend.enuns.Shift;
import com.edutech.backend.enuns.Situation;
import com.edutech.backend.enuns.TeachingState;
import com.edutech.backend.repositories.ClassroomRepository;
import com.edutech.backend.repositories.CoordinatorRepository;
import com.edutech.backend.repositories.StudentRepository;
import com.edutech.backend.repositories.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

	private final ClassroomRepository repositoryClassroom;
	private final StudentRepository repositoryStudent;
	private final TeacherRepository repositoryTeacher;
	private final CoordinatorRepository repositoryCoordinator;

	@Override
	public void run(String... args) throws Exception {

		Classroom c1 = new Classroom(1, "A", 35, Shift.MANHA, 2025, TeachingState.ENSINO_INFANTIL);
		Classroom c2 = new Classroom(5, "A", 30, Shift.TARDE, 2025, TeachingState.ENSINO_FUNDAMENTAL_I);
		Classroom c3 = new Classroom(6, "A", 28, Shift.MANHA, 2025, TeachingState.ENSINO_FUNDAMENTAL_II);
		Classroom c4 = new Classroom(2, "A", 40, Shift.TARDE, 2025, TeachingState.ENSINO_MEDIO);
		Classroom c5 = new Classroom(3, "A", 20, Shift.NOITE, 2025, TeachingState.EDUCAÇÃO_JOVENS_ADULTOS);

		repositoryClassroom.saveAll(Arrays.asList(c1, c2, c3, c4, c5));

		Student s1 = new Student("João da Silva", LocalDate.parse("2003-01-20"), "256.222.222-33", "2556874122235","Rua A, 123", "839555-0001", "joao.resp@gmail.com", "Maria Aparecida", "José da Silva", c1);
		Student s2 = new Student("Ana Beatriz", LocalDate.parse("2010-05-10"), "356.333.444-55", "3556678899001","Rua B, 456", "839555-0002", "ana.resp@gmail.com", "Clara Beatriz", "Pedro Beatriz", c2);
		Student s3 = new Student("Carlos Eduardo", LocalDate.parse("2007-11-30"), "456.555.666-77", "4556678899002","Rua C, 789", "839555-0003", "carlos.resp@gmail.com", "Eliana Eduardo", "Carlos Eduardo Sr.", c3);
		Student s4 = new Student("Mariana Souza", LocalDate.parse("2005-07-25"), "556.777.888-99", "5556678899003","Rua D, 321", "839555-0004", "mariana.resp@gmail.com", "Fernanda Souza", "Ricardo Souza", c4);
		Student s5 = new Student("Pedro Henrique", LocalDate.parse("1998-12-12"), "656.888.999-00", "6556678899004","Rua E, 654", "839555-0005", "pedro.resp@gmail.com", "Ana Henrique", "José Henrique", c5);

		repositoryStudent.saveAll(Arrays.asList(s1, s2, s3, s4, s5));

		Coordinator co1 = Coordinator.builder()
			    .name("Mariana Alves Costa")
			    .cpf("123.456.789-00")
			    .telephone("83991234561")
			    .status(Situation.ATIVO)
			    .dateBirth(LocalDate.parse("1990-03-15"))
			    .rg("101010")
			    .address("Rua das Flores, 200 - João Pessoa/PB")
			    .matriculation("1001001")
			    .formedCourse("Pedagogia")
			    .modality(TeachingState.ENSINO_INFANTIL)
			    .build();
		
		Coordinator co2 = Coordinator.builder()
			    .name("Renata Lima Duarte")
			    .cpf("234.567.890-11")
			    .telephone("83991345678")
			    .status(Situation.ATIVO)
			    .dateBirth(LocalDate.parse("1985-07-22"))
			    .rg("202020")
			    .address("Av. Central, 345 - Patos/PB")
			    .matriculation("2002002")
			    .formedCourse("Letras")
			    .modality(TeachingState.ENSINO_FUNDAMENTAL_I)
			    .build();
		
		Coordinator co3 = Coordinator.builder()
			    .name("Tiago Ferreira Nunes")
			    .cpf("345.678.901-22")
			    .telephone("83991456789")
			    .status(Situation.ATIVO)
			    .dateBirth(LocalDate.parse("1980-11-10"))
			    .rg("303030")
			    .address("Rua Rio Branco, 789 - Sousa/PB")
			    .matriculation("3003003")
			    .formedCourse("Matemática")
			    .modality(TeachingState.ENSINO_FUNDAMENTAL_II)
			    .build();
		
		Coordinator co4 = Coordinator.builder()
			    .name("Lucas Henrique Barreto")
			    .cpf("111.222.333-44")
			    .telephone("83991234567")
			    .status(Situation.ATIVO)
			    .dateBirth(LocalDate.parse("1988-06-25"))
			    .rg("789456")
			    .address("Av. Brasil, 450 - Campina Grande/PB")
			    .matriculation("7896542")
			    .formedCourse("História")
			    .modality(TeachingState.ENSINO_MEDIO)
			    .build();
		
		Coordinator co5 = Coordinator.builder()
			    .name("Fernanda de Souza Melo")
			    .cpf("456.789.012-33")
			    .telephone("83991567890")
			    .status(Situation.ATIVO)
			    .dateBirth(LocalDate.parse("1978-02-18"))
			    .rg("404040")
			    .address("Rua da Paz, 123 - Guarabira/PB")
			    .matriculation("4004004")
			    .formedCourse("Sociologia")
			    .modality(TeachingState.EDUCAÇÃO_JOVENS_ADULTOS)
			    .build();
		
		repositoryCoordinator.saveAll(Arrays.asList(co1, co2, co3, co4, co5));
		
		
		Teacher t1 = Teacher.builder()
				.name("José da Silva Morais")
				.cpf("174.524.556-55")
				.telephone("83598542145")
				.status(Situation.ATIVO)
				.dateBirth(LocalDate.parse("2002-01-22"))
				.workloadTotal(30).rg("222222")
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
				.workloadTotal(40).rg("123123")
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
				.workloadTotal(20).rg("456456")
				.address("Rua das Acácias, 345 - Campina Grande/PB")
				.matriculation("145878")
				.formedCourse("Licenciatura em História")
				.build();

		Teacher t4 = Teacher.builder()
				.name("Fernanda Lopes Andrade").cpf("987.654.321-99")
				.telephone("83991234567")
				.status(Situation.ATIVO)
				.dateBirth(LocalDate.parse("1995-12-05"))
				.workloadTotal(25).rg("789789")
				.address("Rua do Sol, 789 - Sousa/PB")
				.matriculation("145879")
				.formedCourse("Licenciatura em Ciências Biológicas")
				.build();

		repositoryTeacher.saveAll(Arrays.asList(t1, t2, t3, t4));
		

	}

}
