package com.gosolar2.repository;

import com.gosolar2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Joshua King on 4/8/17.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndPassword (String email, String password);
}