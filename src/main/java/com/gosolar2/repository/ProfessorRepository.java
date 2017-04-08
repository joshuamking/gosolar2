package com.gosolar2.repository;

import com.gosolar2.model.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Joshua King on 4/8/17.
 */
@Repository
@Transactional
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
}
