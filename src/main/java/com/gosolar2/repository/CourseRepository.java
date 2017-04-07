package com.gosolar2.repository;

import com.gosolar2.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {

}