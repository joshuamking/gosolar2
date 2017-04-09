package com.gosolar2.model;

import javax.persistence.*;

/**
 * Created by pranathi on 4/7/17.
 */
@Entity
@Table (name = "course")
public class Transcript {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long    id;
	private boolean isOfficial;
	private boolean isGraduate;

	public Long getId () {
		return id;
	}

	public boolean isOfficial () {
		return isOfficial;
	}

	public void setOfficial (boolean isOfficial) {
		this.isOfficial = isOfficial;
	}

	public boolean getIsGraduate () {
		return isGraduate;
	}

	public void setIsGraduate (boolean isGraduate) {
		this.isGraduate = isGraduate;
	}
}
