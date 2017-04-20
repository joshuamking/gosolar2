package com.gosolar2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by pranathi on 4/7/17.
 */
@Entity
@Table (name = "transcript")
public class Transcript {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private                                                        Long      id;
	private                                                        boolean   isOfficial;
	private                                                        LocalDate dateRequested;
	@ManyToOne (fetch = FetchType.EAGER, optional = false) private Student   student;

	public Transcript (boolean isOfficial) {
		this.isOfficial = isOfficial;
		this.dateRequested = LocalDate.now();
	}

	public Transcript () {
	}

	public LocalDate getDateRequested () {
		return dateRequested;
	}

	public void setDateRequested (LocalDate dateRequested) {
		this.dateRequested = dateRequested;
	}

	public Long getId () {
		return id;
	}

	public boolean isOfficial () {
		return isOfficial;
	}

	public void setOfficial (boolean isOfficial) {
		this.isOfficial = isOfficial;
	}

	@JsonIgnore
	public Student getStudent () {
		return student;
	}

	public void setStudent (Student student) {
		this.student = student;
	}
}
