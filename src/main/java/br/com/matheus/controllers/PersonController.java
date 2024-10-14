package br.com.matheus.controllers;

import java.util.List;

import br.com.matheus.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.matheus.data.vo.v1.PersonVO;
import br.com.matheus.services.PersonServices;
//@CrossOrigin - É um metodo de habilitar cross para a api inteira
@RestController // RestController add um response body e um controller.
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoint for managing people.")
public class PersonController {

	@Autowired
	private PersonServices service;
	// Private PersonServices teste = new PersonServices() | já está sendo feito
	// pelo autowired e pelo service.

	@CrossOrigin(origins = "http://localhost:8080") //Permitindo acesso apenas para esse link
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a Person", description = "Finds a Person",
			tags = {"People"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
						content = @Content(schema = @Schema(implementation = PersonVO.class))),
				@ApiResponse(description = "No content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
	public PersonVO findById(@PathVariable Long id) {

		return service.findById(id);
	}

	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all people", description = "Finds all people",
			tags = {"People"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = {
									@Content(
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
									)
							}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
	public List<PersonVO> findByAll() throws Exception {
		return service.findAll();
	}

	@CrossOrigin(origins = {"http://localhost:8080", "https://matheus.com.br"})
	@Operation(summary = "Adds a new Person by parsing in a JSON, XML or YML", description = "Adds a new Person by parsing in a JSON, XML or YML",
			tags = {"People"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(schema = @Schema(implementation = PersonVO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
	@PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			consumes =  {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		return service.create(person);
	}

	@Operation(summary = "Updates a Person", description = "Updates a Person",
			tags = {"People"},
			responses = {
					@ApiResponse(description = "Updated", responseCode = "200",
							content = @Content(schema = @Schema(implementation = PersonVO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
	@PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}

	@PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Disables a specific Person by id", description = "Disables a specific Person by id",
			tags = {"People"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content(schema = @Schema(implementation = PersonVO.class))),
					@ApiResponse(description = "No content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
	public PersonVO disablePerson(@PathVariable(value = "id") Long id) {

		return service.disablePerson(id);
	}

	@Operation(summary = "Deletes a Person", description = "Deletes a Person",
			tags = {"People"},
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", 	content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
			})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {

		service.delete(id);
		return ResponseEntity.noContent().build(); // Retornando status code correto
	}
}
