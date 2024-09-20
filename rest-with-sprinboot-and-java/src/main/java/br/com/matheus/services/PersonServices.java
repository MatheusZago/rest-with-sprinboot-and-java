package br.com.matheus.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.matheus.exceptions.ResourceNotFoundException;
import br.com.matheus.model.Person;
import br.com.matheus.repositories.PersonRepository;

@Service // Para que o Springboot encare esse objeto como um que vai ser injetado em
			// runtime na app
//Ou seja, ele não precisa fazer o Person Service = new Service(), isso já faz ele ficar instanciado em todos os lugares que precisam dele
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired // Tbm serve pra injetar p cpntroller
	private PersonRepository personRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() throws Exception {
		logger.info("Finding all people");
		return personRepository.findAll();
	}

	public Person findById(Long id) {
		logger.info("Finding one person!");

		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	}

//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Person create(Person person) throws Exception {

		logger.info("Creating one person");
		return personRepository.save(person);
	}

//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Person update(Person person) throws Exception {
		logger.info("Updating one person");

		Person entity = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		return personRepository.save(entity);
	}

//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(Long id) throws Exception {
		logger.info("Deleting one person");

		Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		personRepository.delete(entity);
	}

}
