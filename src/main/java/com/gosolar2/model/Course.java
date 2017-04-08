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

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalTime;

/**
 * @author josh
 */
@Entity
@Transactional
@Table (name = "course")
public class Course {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long      id;
	@NotEmpty (message = "Course name is required")
	@Column
	private String    name;
	@Column
	private int       credits;
	@Column
	private String    classNumber;
	@Column
	private String    description;
	@Column
	private String    degreeLevel;
	@Column
	private String    subjectCode;
	@Column
	private String    building;
	@Column
	private String    roomNumber;
	@Column
	private String    term;
	@Column
	private LocalTime startTime;
	@Column
	private LocalTime endTime;
	@Column
	private String    days;
	@Column
	private int       crn;
	@Column
	private int       maxCapacity;

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