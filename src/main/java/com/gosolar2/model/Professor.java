package com.gosolar2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gosolar2.converters.LocalTimeToIntConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by Joshua King on 4/8/17.
 */
@Entity
@Table (name = "professor")
public class Professor extends User {
	@Convert (converter = LocalTimeToIntConverter.class) private LocalTime   officeHoursStartTime;
	@Convert (converter = LocalTimeToIntConverter.class) private LocalTime   officeHoursEndTime;
	private                                                      String      officeLocation;
	@OneToMany (targetEntity = Course.class, mappedBy = "professor")
	private                                                      Set<Course> classes;

	@JsonIgnore
	public Set<Course> getClasses () {
		return classes;
	}

	public void setClasses (Set<Course> classes) {
		this.classes = classes;
	}

	public LocalTime getOfficeHoursStartTime () {
		return officeHoursStartTime;
	}

	public void setOfficeHoursStartTime (LocalTime officeHoursStartTime) {
		this.officeHoursStartTime = officeHoursStartTime;
	}

	public LocalTime getOfficeHoursEndTime () {
		return officeHoursEndTime;
	}

	public void setOfficeHoursEndTime (LocalTime officeHoursEndTime) {
		this.officeHoursEndTime = officeHoursEndTime;
	}

	public String getOfficeLocation () {
		return officeLocation;
	}

	public void setOfficeLocation (String officeLocation) {
		this.officeLocation = officeLocation;
	}
}
