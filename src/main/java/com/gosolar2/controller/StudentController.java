package com.gosolar2.controller;

import com.gosolar2.model.Course;
import com.gosolar2.model.Student;
import com.gosolar2.repository.CourseRepository;
import com.gosolar2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Joshua King on 4/8/17.
 */
@Controller
@RequestMapping ("/student")
public class StudentController {
	private final StudentRepository studentRepository;
	private final CourseRepository  courseRepository;

	@Autowired
	public StudentController (@Qualifier ("studentRepository") StudentRepository studentRepository, @Qualifier ("courseRepository") CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
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

	@GetMapping ("/{studentId}/registerForCourse/{courseId}")
	@ResponseBody
	public Student registerForCourse (@PathVariable ("studentId") Long studentId, @PathVariable ("courseId") Long courseId, HttpServletResponse response) throws IOException {
		try {
			Student student = studentRepository.findOne(studentId);
			Course course = courseRepository.findOne(courseId);
			course.getStudents().add(student);
			courseRepository.save(course);
			return student;
		}
		catch (NullPointerException e) {
			response.sendError(404, e.getMessage());
			return null;
		}
	}

	@GetMapping ("/{studentId}/unregisterForCourse/{courseId}")
	@ResponseBody
	public Student unregisterForCourse (@PathVariable ("studentId") Long studentId, @PathVariable ("courseId") Long courseId) {
		Student student = studentRepository.findOne(studentId);
		Course course = courseRepository.findOne(courseId);
		course.getStudents().remove(student);
		courseRepository.save(course);
		return student;
	}

	@DeleteMapping ("/{studentId}")
	@ResponseBody
	public void deleteById (@PathVariable ("studentId") Long studentId) {
		studentRepository.delete(studentId);
	}
}
