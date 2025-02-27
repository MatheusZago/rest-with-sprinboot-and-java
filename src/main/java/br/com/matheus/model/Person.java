package br.com.matheus.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Para dizer que é uma entidade de banco de dados
@Table(name = "person") // Para dizer qual tabela ele se refere
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // Para falar q é a primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Fazendo o auto increment
	private Long id;
	@Column(name = "first_name", nullable = false, length = 80)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 80)
	private String lastName;
	@Column(name = "address", nullable = false, length = 100)
	private String address;
	@Column(name = "gender", nullable = false, length = 6)
	private String gender;
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	public Person() {

	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getEnabled() {return enabled;}

	public void setEnabled(Boolean enabled) {this.enabled = enabled;}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(gender, person.gender) && Objects.equals(enabled, person.enabled);
	}
}
