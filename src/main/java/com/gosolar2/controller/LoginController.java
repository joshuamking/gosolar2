package com.gosolar2.controller;

import com.gosolar2.model.User;
import com.gosolar2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Joshua King on 4/14/17.
 */
@Controller
@RequestMapping ("/")
public class LoginController {
	private final UserRepository userRepository;

	@Autowired public LoginController (@Qualifier ("userRepository") UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping ("/login")
	@ResponseBody
	public Object login (@RequestBody HashMap map, HttpServletResponse response) throws IOException {
		String email = (String) map.get("email");
		String password = (String) map.get("password");

		User user = userRepository.findByEmailAndPassword(email, password);

		if (user == null) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		else {
			response.setStatus(HttpServletResponse.SC_OK);
			return user;
		}
	}
}