/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gosolar2.controller;

import com.gosolar2.model.Course;
import com.gosolar2.model.Professor;
import com.gosolar2.repository.CourseRepository;
import com.gosolar2.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rob Winch
 * @author Doo-Hwan Kwak
 */
@Controller
@RequestMapping ("/course")
public class CourseController {
	private final CourseRepository    courseRepository;
	private final ProfessorRepository professorRepository;

	@Autowired public CourseController (@Qualifier ("courseRepository") CourseRepository courseRepository,
										@Qualifier ("professorRepository") ProfessorRepository professorRepository) {
		this.courseRepository = courseRepository;
		this.professorRepository = professorRepository;
	}

	@PostMapping ({"/new", "/create"})
	@ResponseBody
	public Course save (@RequestBody Course newCourse) {
		return courseRepository.save(newCourse);
	}

	@GetMapping ("")
	@ResponseBody
	public Iterable<Course> list () {
		return this.courseRepository.findAll();
	}

	@GetMapping ("findByProfessorId/{professorId}")
	@ResponseBody
	public Iterable<Course> findCoursesByProfessorId (@PathVariable ("professorId") long professorId) {
		Professor professor = professorRepository.findOne(professorId);
		return courseRepository.findByProfessor(professor);
	}

	@GetMapping ("/{courseId}")
	@ResponseBody
	public Course findById (@PathVariable ("courseId") long courseId) {
		return courseRepository.findOne(courseId);
	}

	@DeleteMapping ("/{courseId}")
	@ResponseBody
	public void deleteById (@PathVariable ("courseId") long courseId) {
		courseRepository.delete(courseId);
	}
}
