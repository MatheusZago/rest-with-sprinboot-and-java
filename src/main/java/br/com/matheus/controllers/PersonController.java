package br.com.matheus.controllers;

import java.util.List;

import br.com.matheus.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.data.vo.v1.PersonVO;
import br.com.matheus.services.PersonServices;

@RestController // RestController add um response body e um controller.
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonServices service;
	// Private PersonServices teste = new PersonServices() | já está sendo feito
	// pelo autowired e pelo service.

	//Marcando que ele pode produzir tanto Json qnt XML
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	public PersonVO findById(@PathVariable Long id) throws Exception {

		return service.findById(id);
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	public List<PersonVO> findByAll() throws Exception {
		return service.findAll();
	}

	@PostMapping(produces = {MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML},
			consumes =  {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		return service.create(person);
	}

	@PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML},
			consumes = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {

		service.delete(id);
		return ResponseEntity.noContent().build(); // Retornando status code correto
	}
}
