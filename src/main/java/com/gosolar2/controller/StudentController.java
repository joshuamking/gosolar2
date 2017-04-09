package com.gosolar2.controller;

import com.gosolar2.model.Student;
import com.gosolar2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping ("")
	@ResponseBody
	public Iterable<Student> list () {
		return this.studentRepository.findAll();
	}

	@PostMapping ({"/new", "/create"})
	@ResponseBody
	public Student createNew (@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@GetMapping ("/{studentId}")
	@ResponseBody
	public Student findById (@PathVariable ("studentId") Long studentId) {
		return studentRepository.findOne(studentId);
	}

	@DeleteMapping ("/{studentId}")
	@ResponseBody
	public void deleteById (@PathVariable ("studentId") Long studentId) {
		studentRepository.delete(studentId);
	}
}
