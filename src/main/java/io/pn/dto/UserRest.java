package io.pn.dto;

public class UserRest {
	String firstName;
	String lastName;
	String userId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserRest(String firstName, String lastName, String userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserRest [firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + "]";
	}

}
