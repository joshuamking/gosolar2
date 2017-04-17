package com.gosolar2.model;

import javax.persistence.*;

/**
 * Created by pranathi on 4/7/17.
 */

@Entity
@Table (name = "emergency_contact")
public class EmergencyContact {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private                               Long   id;
	private                               String name;
	private                               String address;
	private                               String phoneNumber;
	private                               String relationship;
	@ManyToOne (optional = false) private User   user;

	public Long getId () {
		return id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getAddress () {
		return address;
	}

	public void setAddress (String address) {
		this.address = address;
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setPhoneNumber (String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRelationship () {
		return relationship;
	}

	public void setRelationship (String relationship) {
		this.relationship = relationship;
	}

	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}
}
