package br.com.matheus.integrationTests.controller.withjson;

import br.com.matheus.integrationTests.testsContainer.AbstractIntegrationTest;
import br.com.matheus.integrationTests.vo.AccountCredentialsVO;
import br.com.matheus.integrationTests.vo.PersonVO;
import br.com.matheus.integrationTests.vo.TokenVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import configs.TesteConfigs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )
public class PersonControllerJsonTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static PersonVO person;

	@BeforeAll
	public static void setUp(){
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		person = new PersonVO();
	}



	@Test
	@Order(1)
	public void testCreate() throws JsonProcessingException, JsonMappingException  {
		mockPerson();

		var content = given().spec(specification)
				.contentType(TesteConfigs.CONTENT_TYPE_JSON)
				.header(TesteConfigs.HEADER_PARAM_ORIGIN, TesteConfigs.ORIGIN_MATHEUS)
					.body(person)
					.when()
					.post()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();

		PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
		person = createdPerson;

		assertNotNull(createdPerson);
		assertNotNull(createdPerson.getId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());

		assertTrue(createdPerson.getId() > 0);

		assertEquals("Richard",createdPerson.getFirstName());
		assertEquals("Stallman",createdPerson.getLastName());
		assertEquals("New York City - EUA",createdPerson.getAddress());
		assertEquals("Male",createdPerson.getGender());
	}

	@Test
	@Order(2)
	public void testCreateWithWrongOrigin() throws JsonProcessingException, JsonMappingException  {
		mockPerson();

		var content = given().spec(specification)
				.contentType(TesteConfigs.CONTENT_TYPE_JSON)
				.header(TesteConfigs.HEADER_PARAM_ORIGIN, TesteConfigs.ORIGIN_INVALIDO)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();


		assertNotNull(content);
		assertEquals("Invalid CORS request", content);
	}

	@Test
	@Order(3)
	public void testFindById() throws JsonProcessingException, JsonMappingException  {
		mockPerson();

		var content = given().spec(specification)
				.contentType(TesteConfigs.CONTENT_TYPE_JSON)
				.header(TesteConfigs.HEADER_PARAM_ORIGIN, TesteConfigs.ORIGIN_MATHEUS)				.pathParams("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();

		PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
		person = persistedPerson;

		assertNotNull(persistedPerson);
		assertNotNull(persistedPerson.getId());
		assertNotNull(persistedPerson.getFirstName());
		assertNotNull(persistedPerson.getLastName());
		assertNotNull(persistedPerson.getAddress());
		assertNotNull(persistedPerson.getGender());

		assertTrue(persistedPerson.getId() > 0);

		assertEquals("Richard",persistedPerson.getFirstName());
		assertEquals("Stallman",persistedPerson.getLastName());
		assertEquals("New York City - EUA",persistedPerson.getAddress());
		assertEquals("Male",persistedPerson.getGender());
	}

	@Test
	@Order(4)
	public void testFindByIdWithWrongOrigin() throws JsonProcessingException, JsonMappingException  {
		mockPerson();

		var content = given().spec(specification)
				.contentType(TesteConfigs.CONTENT_TYPE_JSON)
				.header(TesteConfigs.HEADER_PARAM_ORIGIN, TesteConfigs.ORIGIN_INVALIDO)
				.pathParams("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();

		assertNotNull(content);
		assertEquals("Invalid CORS request", content);
	}

	private void mockPerson() {
		person.setFirstName("Richard");
		person.setLastName("Stallman");
		person.setAddress("New York City - EUA");
		person.setGender("Male");
	}

}
