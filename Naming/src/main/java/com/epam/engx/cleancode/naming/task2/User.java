package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	private Date birthday;

	private String name;

	private boolean admin;

	private User[] subordinates;

	private int ownRating;

	public User(String newName) {
		super();
		this.name = newName;
	}

	@Override
	public String toString() {
		return "User [dBirth=" + birthday + ", sName=" + name + ", bAdmin=" + admin + ", subordinateArray="
				+ Arrays.toString(subordinates) + ", iRating=" + ownRating + "]";
	}

}
