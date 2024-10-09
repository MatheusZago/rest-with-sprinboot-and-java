package br.com.matheus.services;

import java.util.List;
import java.util.logging.Logger;

import br.com.matheus.controllers.PersonController;
import br.com.matheus.exceptions.RequiredObjectIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.matheus.data.vo.v1.PersonVO;
import br.com.matheus.exceptions.ResourceNotFoundException;
import br.com.matheus.mapper.DozerMapper;
import br.com.matheus.model.Person;
import br.com.matheus.repositories.PersonRepository;

@Service // Para que o Springboot encare esse objeto como um que vai ser injetado em
			// runtime na app
//Ou seja, ele não precisa fazer o PersonVO Service = new Service(), isso já faz ele ficar instanciado em todos os lugares que precisam dele
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired // Tbm serve pra injetar p cpntroller
	private PersonRepository personRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {
		logger.info("Finding all people");
		var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		//Criando stream para passar os links
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		//Usando hateo para criar um link de auto relacionamento
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel()); //Adicionando um endereço para si mesmo
		return vo;

	}

	public PersonVO create(PersonVO personVO) {
		if(personVO == null) throw new RequiredObjectIsNullException();

		logger.info("Creating one person");
		var entity = DozerMapper.parseObject(personVO, Person.class);  

		//Primeiro ele vai salvar, e ai o objeto salvo vai ser covertido para VO PARA A APLICAÇÃO APENAS
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel()); //Adicionando um endereço para si mesmo
		return vo;
	}  

	  public PersonVO update(PersonVO personVO) {
		  if(personVO == null) throw new RequiredObjectIsNullException();

		  logger.info("Updating one person");

		var entity = personRepository.findById(personVO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setAddress(personVO.getAddress());
		entity.setGender(personVO.getGender());

		//Primeiro ele vai salvar, e ai o objeto salvo vai ser covertido para VO PARA A APLICAÇÃO APENAS
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel()); //Adicionando um endereço para si mesmo
		return vo;
	}

	public void delete(Long id)  {
		logger.info("Deleting one person");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		personRepository.delete(entity);
	}

}
