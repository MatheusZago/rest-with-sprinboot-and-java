package br.com.matheus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.matheus.model.Person;

@Service // Para que o Springboot encare esse objeto como um que vai ser injetado em
			// runtime na app
//Ou seja, ele não precisa fazer o Person Service = new Service(), isso já faz ele ficar instanciado em todos os lugares que precisam dele
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public Person findById(String id) {

		logger.info("Finding one person!");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Leandro");
		person.setLastName("Costa");
		person.setAddress("Uberlandia - Minas Gerais - Brasil");
		person.setGender("Male");
		return person;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() throws Exception {
		List<Person> persons = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}

	private Person mockPerson(int i) {

		logger.info("Findind all people");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Some address in Brazil " + i);
		person.setGender("Male");
		return person;
	}

}
