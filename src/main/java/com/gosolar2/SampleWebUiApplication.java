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

@SpringBootApplication
@EnableAutoConfiguration (exclude = {
		JpaRepositoriesAutoConfiguration.class
})
@ComponentScan ("com.gosolar2")
public class SampleWebUiApplication {

	public static void main (String[] args) throws Exception {
		SpringApplication.run(SampleWebUiApplication.class, args);
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

}
