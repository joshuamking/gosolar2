/*
 * Copyright 2012-2017 the original author or authors.
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

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Dave Syer
 */
@Service
@Component
public class InMemoryCourseRepository implements CourseRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, Course> messages = new ConcurrentHashMap<>();

	@Override
	public Iterable<Course> findAll () {
		return this.messages.values();
	}

	@Override
	public Course save (Course course) {
		Long id = course.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			course.setId(id);
		}
		this.messages.put(id, course);
		return course;
	}

	@Override
	public Course findMessage (Long id) {
		return this.messages.get(id);
	}

	@Override
	public void deleteMessage (Long id) {
		this.messages.remove(id);
	}

}
