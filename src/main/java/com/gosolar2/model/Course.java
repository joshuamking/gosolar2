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
	@NotEmpty (message = "Course is required.")
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
	private int       roomNumber;
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
}