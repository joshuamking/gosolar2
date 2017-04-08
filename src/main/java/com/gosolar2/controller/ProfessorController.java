package com.gosolar2.controller;

import com.gosolar2.model.Professor;
import com.gosolar2.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;

/**
 * Created by Joshua King on 4/8/17.
 */
@Controller
@RequestMapping ("/professor")
public class ProfessorController {
	private final ProfessorRepository professorRepository;

	@Autowired public ProfessorController (@Qualifier ("professorRepository") ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	@GetMapping ({"/", ""})
	@ResponseBody
	public Iterable<Professor> list () {
		Professor professor = new Professor();
		professor.setEmail("vapolokov@gsu.edu");
		professor.setFirstName("Vadym");
		professor.setLastName("Apolokov");
		professor.setPassword("1234");
		professor.setPhoneNumber("1234567890");
		professor.setOfficeHoursStartTime(LocalTime.of(13, 30));
		professor.setOfficeHoursEndTime(LocalTime.of(14, 30));
		professor.setOfficeLocation("The location of the office!");

		professorRepository.save(professor);

		return this.professorRepository.findAll();
	}
}
