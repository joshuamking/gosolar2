package com.gosolar2.model;

import javax.persistence.*;
import javax.transaction.Transactional;

/**
 * Created by pranathi on 4/7/17.
 */
@Entity
@Transactional
@Table (name = "course")
public class Transcript {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long    id;
	private boolean isOfficial;
	private boolean isgraduate;

	public Long getId () {
		return id;
	}

	public boolean isOfficial () {
		return isOfficial;
	}

	public void setOfficial (boolean official) {
		isOfficial = official;
	}

	public boolean isIsgraduate () {
		return isgraduate;
	}

	public void setIsgraduate (boolean isgraduate) {
		this.isgraduate = isgraduate;
	}
}
