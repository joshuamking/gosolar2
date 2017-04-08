package com.gosolar2.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Joshua King on 4/7/17.
 */
@Entity
@Table (name = "student")
public class Student extends User {
	private String major;

	public String getMajor () {
		return major;
	}

	public void setMajor (String major) {
		this.major = major;
	}
}