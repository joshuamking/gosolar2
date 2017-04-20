package com.gosolar2.controller;

import com.gosolar2.model.Course;
import com.gosolar2.model.Student;
import com.gosolar2.model.Transcript;
import com.gosolar2.repository.CourseRepository;
import com.gosolar2.repository.StudentRepository;
import com.gosolar2.repository.TranscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Joshua King on 4/8/17.
 */
@Controller
@RequestMapping ("/student")
public class StudentController {
	private final StudentRepository    studentRepository;
	private final CourseRepository     courseRepository;
	private final TranscriptRepository transcriptRepository;

	@Autowired
	public StudentController (@Qualifier ("studentRepository") StudentRepository studentRepository,
							  @Qualifier ("courseRepository") CourseRepository courseRepository,
							  @Qualifier ("transcriptRepository") TranscriptRepository transcriptRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.transcriptRepository = transcriptRepository;
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

	@GetMapping ("/{studentId}/requestTranscript")
	@ResponseBody
	public Student findById (@PathVariable ("studentId") Long studentId,
							 @RequestParam (name = "isOfficial", required = false, defaultValue = "false") boolean isOfficial) {
		Student student = studentRepository.findOne(studentId);
		Transcript transcript = new Transcript(isOfficial);
		transcript.setStudent(student);
		transcript = transcriptRepository.save(transcript);
		student.getTranscripts().add(transcript);
		return student;
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

	@GetMapping ("/{studentId}/getCourses")
	@ResponseBody
	public Set<Course> registerForCourse (@PathVariable ("studentId") Long studentId, HttpServletResponse response) throws IOException {
		try {
			return studentRepository.findOne(studentId).getCourses();
		}
		catch (NullPointerException e) {
			response.sendError(404, e.getMessage());
			return null;
		}
	}

	@DeleteMapping ("/{studentId}")
	@ResponseBody
	public void deleteById (@PathVariable ("studentId") Long studentId) {
		studentRepository.delete(studentId);
	}
}
