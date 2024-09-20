package br.com.matheus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.model.Person;
import br.com.matheus.services.PersonServices;

@RestController // RestController add um response body e um controller.
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices service;
	// Private PersonServices teste = new PersonServices() | já está sendo feito
	// pelo autowired e pelo service.

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable String id) throws Exception {

		return service.findById(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findByAll() throws Exception {
		return service.findAll();
	}

}
