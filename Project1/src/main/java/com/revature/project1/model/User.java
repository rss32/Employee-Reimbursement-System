package com.revature.project1.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String ersUsername;
	private String ersPassword;
	private String firstName;
	private String lastName;
	private String email;
	private String userRole;

	public User() {
		id = 0;
	}

	public User(String fname, String lname, String username, String pass, String email, String role) {
		this();
		ersUsername = username;
		ersPassword = pass;
		firstName = fname;
		lastName = lname;
		this.email = email;
		userRole = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return ersUsername;
	}

	public void setUsername(String ersUsername) {
		this.ersUsername = ersUsername;
	}

	public String getPassword() {
		return ersPassword;
	}

	public void setPassword(String ersPassword) {
		this.ersPassword = ersPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((ersPassword == null) ? 0 : ersPassword.hashCode());
		result = prime * result + ((ersUsername == null) ? 0 : ersUsername.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (ersPassword == null) {
			if (other.ersPassword != null)
				return false;
		} else if (!ersPassword.equals(other.ersPassword))
			return false;
		if (ersUsername == null) {
			if (other.ersUsername != null)
				return false;
		} else if (!ersUsername.equals(other.ersUsername))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ersUsername=" + ersUsername + ", ersPassword=" + ersPassword + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + "]";
	}
}