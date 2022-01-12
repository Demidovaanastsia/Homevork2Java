package com.example.demo;

import com.example.demo.entity.Address;
import com.example.demo.entity.District;
import com.example.demo.entity.EducationalInstitution;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.EducationalInstitutionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("classpath:=/xmlConfig/springConfig.xml")

public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AddressRepository addressRepository,
										DistrictRepository districtRepository,
										EducationalInstitutionRepository educationalInstitutionRepository){
		return args -> {

			District distrit1 = new District("Орджоникидзевский");
			District distrit2 = new District("Ленинский");
			District distrit3 = new District("Чкаловский");
			District distrit4 = new District("Октябрьский");
			District distrit5 = new District("Кировский");
			District distrit6 = new District("Железнодорожный");
			District distrit7 = new District("Верх-Исетский");

			Address address11 = new Address("Совхозная, 6", distrit1);
			Address address12 = new Address("Ульяновская, 8", distrit1);
			Address address13 = new Address("Войкова, 17", distrit1);
			Address address21 = new Address("Радищева, 111", distrit2);
			Address address22 = new Address("Шаумяна, 30", distrit2);
			Address address23 = new Address("Ямская, 4", distrit2);
			Address address31 = new Address("Лучистая, 1", distrit3);
			Address address32 = new Address("Окружная, 88", distrit3);
			Address address33 = new Address("Санаторная, 55", distrit3);
			Address address41 = new Address("Реактивная, 10", distrit4);
			Address address42 = new Address("Кольцовский тракт, 64", distrit4);
			Address address43 = new Address("Тверитина, 7", distrit4);
			Address address51 = new Address("Проезжая, 1", distrit5);
			Address address52 = new Address("Блюхера, 32", distrit5);
			Address address53 = new Address("Сулимова, 20", distrit5);
			Address address61 = new Address("Техническая, 23", distrit6);
			Address address62 = new Address("Коуровская, 7", distrit6);
			Address address63 = new Address("Таватуйская, 9", distrit6);
			Address address71 = new Address("Репина, 11", distrit7);
			Address address72 = new Address("Гурзуфская, 2", distrit7);
			Address address73 = new Address("Металлургов, 55", distrit7);

			EducationalInstitution edInst11 = new EducationalInstitution("МБОУ СОШ 100", address11);
			EducationalInstitution edInst12 = new EducationalInstitution("МБОУ СОШ 115", address12);
			EducationalInstitution edInst21 = new EducationalInstitution("МАОУ СОШ 345", address21);
			EducationalInstitution edInst22 = new EducationalInstitution("Школа №16", address23);
			EducationalInstitution edInst31 = new EducationalInstitution("МАОУ СОШ 201", address32);
			EducationalInstitution edInst32 = new EducationalInstitution("Лицей 115", address33);
			EducationalInstitution edInst41 = new EducationalInstitution("Лицей 1", address41);
			EducationalInstitution edInst42 = new EducationalInstitution("Гимназия №27", address42);
			EducationalInstitution edInst51 = new EducationalInstitution("МБОУ СОШ 111", address52);
			EducationalInstitution edInst52 = new EducationalInstitution("Школа №32", address53);
			EducationalInstitution edInst61 = new EducationalInstitution("МБОУ СОШ 27", address61);
			EducationalInstitution edInst62 = new EducationalInstitution("Гимназия №34", address63);
			EducationalInstitution edInst71 = new EducationalInstitution("Школа №21", address71);
			EducationalInstitution edInst72 = new EducationalInstitution("СУНЦ", address73);

			districtRepository.save(distrit1);
			districtRepository.save(distrit2);
			districtRepository.save(distrit3);
			districtRepository.save(distrit4);
			districtRepository.save(distrit5);
			districtRepository.save(distrit6);
			districtRepository.save(distrit7);

			addressRepository.save(address11);
			addressRepository.save(address12);
			addressRepository.save(address13);
			addressRepository.save(address21);
			addressRepository.save(address22);
			addressRepository.save(address23);
			addressRepository.save(address31);
			addressRepository.save(address32);
			addressRepository.save(address33);
			addressRepository.save(address41);
			addressRepository.save(address42);
			addressRepository.save(address43);
			addressRepository.save(address51);
			addressRepository.save(address52);
			addressRepository.save(address53);
			addressRepository.save(address61);
			addressRepository.save(address62);
			addressRepository.save(address63);
			addressRepository.save(address71);
			addressRepository.save(address72);
			addressRepository.save(address73);

			educationalInstitutionRepository.save(edInst11);
			educationalInstitutionRepository.save(edInst12);
			educationalInstitutionRepository.save(edInst21);
			educationalInstitutionRepository.save(edInst22);
			educationalInstitutionRepository.save(edInst31);
			educationalInstitutionRepository.save(edInst32);
			educationalInstitutionRepository.save(edInst41);
			educationalInstitutionRepository.save(edInst42);
			educationalInstitutionRepository.save(edInst51);
			educationalInstitutionRepository.save(edInst52);
			educationalInstitutionRepository.save(edInst61);
			educationalInstitutionRepository.save(edInst62);
			educationalInstitutionRepository.save(edInst71);
			educationalInstitutionRepository.save(edInst72);
		};
	}
}
