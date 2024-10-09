package br.com.matheus.unittests.mockito.services;

import br.com.matheus.model.Person;
import br.com.matheus.repositories.PersonRepository;
import br.com.matheus.services.PersonServices;
import br.com.matheus.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@TestInstance(Lifecycle.PER_CLASS) //Uma instancia por classe
@ExtendWith(MockitoExtension.class) //apara poder mockar
public class PersonServicesTest {

    MockPerson input;

    @InjectMocks //Realmente está puxando o service
    private PersonServices service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setupMocks(){
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testFindAll(){
        fail("Not yet implemented");
    }

    @Test
    void testFindById(){
        Person person = input.mockEntity(1); //Retorna um new perosn, só que sem Id
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result.getKey()).isNotNull();
        assertThat(result.getLinks()).isNotNull();
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());


    }

    @Test
    void testCreate(){
        fail("Not yet implemented");
    }

    @Test
    void testUpdate(){
        fail("Not yet implemented");
    }

    @Test
    void testDelete(){
        fail("Not yet implemented");
    }
}
