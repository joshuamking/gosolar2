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

package com.gosolar2.mvc;

import com.gosolar2.Course;
import com.gosolar2.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Rob Winch
 * @author Doo-Hwan Kwak
 */
@Controller
@RequestMapping ("/")
public class CourseController {

	private final CourseRepository courseRepository;

	public CourseController (CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@GetMapping ("{name}")
	@ResponseBody
	public String list (@PathVariable ("name") String name) {
//		Iterable<Course> messages = this.courseRepository.findAll();
//		return new ModelAndView("messages/list", "messages", messages);
		Course course = new Course(name);
		courseRepository.save(course);

		return "fuck you " + name;
	}
}