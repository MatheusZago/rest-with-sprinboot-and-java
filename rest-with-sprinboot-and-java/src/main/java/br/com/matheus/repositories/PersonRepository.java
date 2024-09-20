package br.com.matheus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.model.Person;

@Repository // Repository não é mais necessário, mas nn faz mal
public interface PersonRepository extends JpaRepository<Person, Long> {

}
