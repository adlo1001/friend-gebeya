package com.gebeya.Friend.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

//	@JsonProperty("first-name")
	private String firstName;
//	@JsonProperty("last-name")
	private String lastName;

	@Min(value = 18)
	private int age;

	@OneToMany(cascade = CascadeType.ALL)
	@Embedded
	private List<Address> addresses;

	// @JsonIgnore
	private boolean married;

	public Friend() {
		super();
	}

	public Friend(String firstName, String lastName, int age, List<Address> addresses, boolean married) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.addresses = addresses;
		this.married = married;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", addresses=" + addresses + ", married=" + married + "]";
	}

}
