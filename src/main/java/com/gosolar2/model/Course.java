/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.gosolar2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gosolar2.converters.LocalTimeToIntConverter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Joshua King on 4/7/17.
 */
@Entity
@Table (name = "course")
public class Course {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private                                                      Long          id;
	private                                                      String        name;
	private                                                      int           credits;
	private                                                      String        classNumber;
	private                                                      String        description;
	private                                                      String        degreeLevel;
	private                                                      String        subjectCode;
	private                                                      String        building;
	private                                                      String        roomNumber;
	private                                                      String        term;
	@Convert (converter = LocalTimeToIntConverter.class) private LocalTime     startTime;
	@Convert (converter = LocalTimeToIntConverter.class) private LocalTime     endTime;
	private                                                      String        days;
	private                                                      int           crn;
	private                                                      int           maxCapacity;
	@ManyToOne (fetch = FetchType.EAGER, targetEntity = Professor.class)
	@JoinColumn (name = "professorId") private                   Professor     professor;
	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable (name = "student_courses",
			joinColumns = {@JoinColumn (name = "course_id")},
			inverseJoinColumns = {@JoinColumn (name = "student_id")})
	private                                                      List<Student> students;

	public Course (String name, int credits, String subjectCode, String classNumber, String description, String degreeLevel, String term, int crn, int maxCapacity) {
		this.name = name;
		this.credits = credits;
		this.subjectCode = subjectCode;
		this.classNumber = classNumber;
		this.description = description;
		this.degreeLevel = degreeLevel;
		this.term = term;
		this.crn = crn;
		this.maxCapacity = maxCapacity;
	}

	public Course () {
	}

	@JsonIgnore
	public List<Student> getStudents () {
		return students;
	}

	public void setStudents (List<Student> students) {
		this.students = students;
	}

	public Professor getProfessor () {
		return professor;
	}

	public void setProfessor (Professor professor) {
		this.professor = professor;
	}

	public Long getId () {
		return id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getCredits () {
		return credits;
	}

	public void setCredits (int credits) {
		this.credits = credits;
	}

	public String getClassNumber () {
		return classNumber;
	}

	public void setClassNumber (String classNumber) {
		this.classNumber = classNumber;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public String getDegreeLevel () {
		return degreeLevel;
	}

	public void setDegreeLevel (String degreeLevel) {
		this.degreeLevel = degreeLevel;
	}

	public String getSubjectCode () {
		return subjectCode;
	}

	public void setSubjectCode (String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getBuilding () {
		return building;
	}

	public void setBuilding (String building) {
		this.building = building;
	}

	public String getRoomNumber () {
		return roomNumber;
	}

	public void setRoomNumber (String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getTerm () {
		return term;
	}

	public void setTerm (String term) {
		this.term = term;
	}

	public LocalTime getStartTime () {
		return startTime;
	}

	public void setStartTime (LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime () {
		return endTime;
	}

	public void setEndTime (LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getDays () {
		return days;
	}

	public void setDays (String days) {
		this.days = days;
	}

	public int getCrn () {
		return crn;
	}

	public void setCrn (int crn) {
		this.crn = crn;
	}

	public int getMaxCapacity () {
		return maxCapacity;
	}

	public void setMaxCapacity (int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
}