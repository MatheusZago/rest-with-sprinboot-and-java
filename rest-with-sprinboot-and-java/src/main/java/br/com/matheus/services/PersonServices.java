package br.com.matheus.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.matheus.data.vo.v1.PersonVO;
import br.com.matheus.data.vo.v2.PersonVOV2;
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
	public List<PersonVO> findAll() throws Exception {
		logger.info("Finding all people");
		// Ta pegando a lista que teve e transformando todos os elementos em PersonVo
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		return DozerMapper.parseObject(entity, PersonVO.class);

	}

	public PersonVO create(PersonVO personVO) {
		logger.info("Creating one person");

		var entity = DozerMapper.parseObject(personVO, Person.class);

		// Primeiro ele vai salvar, e ai o objeto salvo vai ser covertido para VO PARA A
		// APLICAÇÃO APENAS
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person with V2!");

		var entity = DozerMapper.parseObject(person, Person.class);

		// Primeiro ele vai salvar, e ai o objeto salvo vai ser covertido para VO PARA A
		// APLICAÇÃO APENAS
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVOv2.class);
		return vo;// TODO Auto-generated method stub
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating one person");

		var entity = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		// Primeiro ele vai salvar, e ai o objeto salvo vai ser covertido para VO PARA A
		// APLICAÇÃO APENAS
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one person");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		personRepository.delete(entity);
	}

}
