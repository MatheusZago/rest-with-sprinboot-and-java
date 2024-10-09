package br.com.matheus.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

//Isso aqui é pra escolher a ordem de como vão ser mostradas
@JsonPropertyOrder({"id", "first_name", "last_name", "address",  "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	@Mapping("id") //Ainda precisa ser chamado de id nas funções
	private Long key; //Mudou o nome de id para não conflitar com hateoas
	@JsonProperty("first_name") //Mudando o nome enquanto em json
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	private String address;
	//@JsonIgnore
	private String gender;

	public PersonVO() {

	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
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

	@Override
	public int hashCode() {
		return Objects.hash(key);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return key == other.key;
	}

}
