package com.gosolar2.model;

import javax.persistence.*;

/**
 * Created by Joshua King on 4/7/17.
 */
@Entity
@Table (name = "user")
@Inheritance (strategy = InheritanceType.JOINED)
public class User {
	@Id @GeneratedValue (strategy = GenerationType.AUTO) private Long   id;
	private                                                      String firstName;
	private                                                      String lastName;
	private                                                      String email;
	private                                                      String password;
	private                                                      String phoneNumber;
//	@ManyToMany (targetEntity = Course.class/*, mappedBy = "id"*/) private Set<Course> classes;


	public Long getId () {
		return id;
	}

	public String getFirstName () {
		return firstName;
	}

	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}

	public String getLastName () {
		return lastName;
	}

	public void setLastName (String lastName) {
		this.lastName = lastName;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}