package com.gosolar2.controller;

import com.gosolar2.model.Professor;
import com.gosolar2.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Joshua King on 4/8/17.
 */
@Controller
@RequestMapping ("/professor")
public class ProfessorController {
	private final ProfessorRepository professorRepository;

	@Autowired
	public ProfessorController (@Qualifier ("professorRepository") ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	@GetMapping ("")
	@ResponseBody
	public Iterable<Professor> list () {
		return this.professorRepository.findAll();
	}

	@PostMapping ({"/new", "/create"})
	@ResponseBody
	public Professor createNew (@RequestBody Professor professor) {
		return professorRepository.save(professor);
	}

	@GetMapping ("/{professorId}")
	@ResponseBody
	public Professor findById (@PathVariable ("professorId") Long professorId) {
		return professorRepository.findOne(professorId);
	}

	@DeleteMapping ("/{professorId}")
	@ResponseBody
	public void deleteById (@PathVariable ("professorId") Long professorId) {
		professorRepository.delete(professorId);
	}
}