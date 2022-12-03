package com.epam.training.ticketservice.core.user.persistence.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

	@Test
	void testConstructor() {
		// Given
		String username = "user";
		String password = "password";
		User.Role role = User.Role.USER;

		// When
		User underTest = new User(username,password,role);

		// Then
		Assertions.assertEquals(username, underTest.getUsername());
		Assertions.assertEquals(password, underTest.getPassword());
		Assertions.assertEquals(role, underTest.getRole());
	}
}
