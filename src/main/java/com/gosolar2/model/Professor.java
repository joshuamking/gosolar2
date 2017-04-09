package com.gosolar2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gosolar2.converters.LocalTimeToIntConverter;

import javax.persistence.*;
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
	@OneToMany (cascade = CascadeType.ALL, targetEntity = Course.class, mappedBy = "professor")
	private                                                      Set<Course> classes;

	@JsonCreator (mode = JsonCreator.Mode.PROPERTIES)
	public Professor (@JsonProperty ("officeHoursStartTime") int officeHoursStartTime, @JsonProperty ("officeHoursEndTime") int officeHoursEndTime) {
		this.officeHoursStartTime = LocalTime.ofSecondOfDay(officeHoursStartTime);
		this.officeHoursEndTime = LocalTime.ofSecondOfDay(officeHoursEndTime);
	}

	public Professor () {
	}

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
