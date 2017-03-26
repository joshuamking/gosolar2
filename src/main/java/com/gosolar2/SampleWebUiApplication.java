/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gosolar2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootApplication
@EnableAutoConfiguration (exclude = {
		JpaRepositoriesAutoConfiguration.class
})
@Controller
@ComponentScan ("com.gosolar2")
public class SampleWebUiApplication {

	public static void main (String[] args) throws Exception {
		SpringApplication.run(SampleWebUiApplication.class, args);

		try {
			Runtime.getRuntime().exec("open http://localhost:8000/index.html");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Bean
	public Converter<String, Course> courseConverter () {
		return new Converter<String, Course>() {
			@Override public Course convert (String id) {
				return SampleWebUiApplication.this.courseRepository().findMessage(Long.valueOf(id));
			}
		};
	}

	@Bean
	public CourseRepository courseRepository () {
		return new InMemoryCourseRepository();
	}

	@GetMapping ("/**")
	@ResponseBody
	public String home (HttpServletRequest request) throws IOException {
		String path = request.getServletPath();
		if (!path.contains(".")) { path += "/index.html"; }
		File file = new File("src/main/resources/site/" + path);
		return Files.readAllLines(file.toPath()).stream().map(s -> s + "\n").reduce(String::concat).get();
	}
}