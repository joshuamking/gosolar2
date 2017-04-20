package com.gosolar2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gosolar2.enums.UserType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Joshua King on 4/7/17.
 */
@Entity
@Table (name = "student")
public class Student extends User {
	private String          major;
	private boolean         isTA;
	@ManyToMany (fetch = FetchType.EAGER, mappedBy = "students", cascade = CascadeType.ALL)
	private Set<Course>     courses;
	@OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Transcript.class, mappedBy = "student")
	private Set<Transcript> transcripts;

	public Student () {
		setUserType(UserType.Student);
	}

	public Set<Transcript> getTranscripts () {
		return transcripts;
	}

	public void setTranscripts (Set<Transcript> transcripts) {
		this.transcripts = transcripts;
	}

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
	public Set<Course> getCourses () {
		return courses;
	}

	public void setCourses (Set<Course> courses) {
		this.courses = courses;
	}
}