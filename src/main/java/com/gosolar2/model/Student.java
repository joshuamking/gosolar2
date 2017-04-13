package com.gosolar2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Joshua King on 4/7/17.
 */
@Entity
@Table (name = "student")
public class Student extends User {
	private String       major;
	private boolean      isTA;
	@ManyToMany (mappedBy = "students", cascade = CascadeType.ALL)
	private List<Course> courses;

	public boolean getIsTA () {
		return isTA;
	}

	public void setIsTA (boolean TA) {
		isTA = TA;
	}

	public String getMajor () {
		return major;
	}

	public void setMajor (String major) {
		this.major = major;
	}

	@JsonIgnore
	public List<Course> getCourses () {
		return courses;
	}

	public void setCourses (List<Course> courses) {
		this.courses = courses;
	}
}