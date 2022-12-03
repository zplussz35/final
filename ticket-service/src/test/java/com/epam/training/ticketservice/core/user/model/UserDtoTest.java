package com.epam.training.ticketservice.core.user.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.epam.training.ticketservice.core.user.persistence.entity.User;

public class UserDtoTest {

	@Test
	void testConstructor() {
		// Given
		String username = "user";
		User.Role role = User.Role.USER;

		// When
		UserDto underTest = new UserDto(username,role);

		// Then
		Assertions.assertEquals(username, underTest.getUsername());
		Assertions.assertEquals(role, underTest.getRole());
	}

}
