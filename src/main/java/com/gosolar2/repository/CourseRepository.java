package com.gosolar2.repository;

import com.gosolar2.model.Course;
import com.gosolar2.model.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {
	ArrayList<Course> findByProfessor (Professor professor);
}