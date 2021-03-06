package com.gosolar2.controller;

import com.gosolar2.model.Course;
import com.gosolar2.model.EmergencyContact;
import com.gosolar2.model.Student;
import com.gosolar2.model.Transcript;
import com.gosolar2.repository.CourseRepository;
import com.gosolar2.repository.EmergencyContactRepository;
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
	private final StudentRepository          studentRepository;
	private final CourseRepository           courseRepository;
	private final TranscriptRepository       transcriptRepository;
	private final EmergencyContactRepository emergencyContactRepository;

	@Autowired
	public StudentController (@Qualifier ("studentRepository") StudentRepository studentRepository,
							  @Qualifier ("courseRepository") CourseRepository courseRepository,
							  @Qualifier ("transcriptRepository") TranscriptRepository transcriptRepository,
							  @Qualifier ("emergencyContactRepository") EmergencyContactRepository emergencyContactRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.transcriptRepository = transcriptRepository;
		this.emergencyContactRepository = emergencyContactRepository;
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
			return null;
		}
	}

	@GetMapping ("/{studentId}/unregisterForCourse/{courseId}")
	@ResponseBody
	public Student unregisterForCourse (@PathVariable ("studentId") Long studentId, @PathVariable ("courseId") Long courseId, HttpServletResponse response) throws IOException {
		try {
			Student student = studentRepository.findOne(studentId);
			Course course = courseRepository.findOne(courseId);
			course.getStudents().remove(student);
			courseRepository.save(course);
			return student;
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	@GetMapping ("/{studentId}/getCourses")
	@ResponseBody
	public Set<Course> registerForCourse (@PathVariable ("studentId") Long studentId, HttpServletResponse response) throws IOException {
		try {
			return studentRepository.findOne(studentId).getCourses();
		}
		catch (NullPointerException e) {
			return null;
		}
	}

	@PostMapping ("/{studentId}/newEmergencyContact")
	@ResponseBody
	public Student createNewEmergencyContact (@RequestBody EmergencyContact emergencyContact, @PathVariable ("studentId") Long studentId) {
		Student student = studentRepository.findOne(studentId);
		emergencyContact.setUser(student);
		emergencyContactRepository.save(emergencyContact);
		student.getEmergencyContacts().add(emergencyContact);
		return studentRepository.save(student);
	}

	@GetMapping ("/{studentId}/removeEmergencyContact/{emergencyContactId}")
	@ResponseBody
	public Student removeEmergencyContact (@PathVariable ("studentId") Long studentId, @PathVariable ("emergencyContactId") Long emergencyContactId) {
		try {
			EmergencyContact emergencyContact = emergencyContactRepository.findOne(emergencyContactId);
			Student student = studentRepository.findOne(studentId);
			Set<EmergencyContact> emergencyContacts = student.getEmergencyContacts();
			emergencyContacts.remove(emergencyContact);
			emergencyContactRepository.delete(emergencyContact);
			student.setEmergencyContacts(emergencyContacts);
			return studentRepository.save(student);
		}
		catch (NullPointerException e) {
			return null;
		}
	}


	@DeleteMapping ("/{studentId}")
	@ResponseBody
	public void deleteById (@PathVariable ("studentId") Long studentId) {
		studentRepository.delete(studentId);
	}
}
