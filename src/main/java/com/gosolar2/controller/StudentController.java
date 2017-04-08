package com.gosolar2.controller;

import com.gosolar2.model.Student;
import com.gosolar2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Joshua King on 4/8/17.
 */
@Controller
@RequestMapping ("/student")
public class StudentController {
	private final StudentRepository studentRepository;

	@Autowired public StudentController (@Qualifier ("studentRepository") StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping ("/")
	@ResponseBody
	public Iterable<Student> list () {
		Student user = new Student();
		user.setMajor("Computer Science");
		user.setEmail("pvenigandla2@student.gsu.edu");
		user.setFirstName("Pranathi");
		user.setLastName("Venigandla");
		user.setPassword("1234");
		user.setPhoneNumber("1234567890");

		studentRepository.save(user);

		return this.studentRepository.findAll();
	}
}
