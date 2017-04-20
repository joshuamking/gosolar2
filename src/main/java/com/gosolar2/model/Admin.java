package com.gosolar2.model;

import com.gosolar2.enums.UserType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Joshua King on 4/20/17.
 */
@Entity
@Table (name = "admin")
public class Admin extends User {
	public Admin () {
		setUserType(UserType.Admin);
	}
}
