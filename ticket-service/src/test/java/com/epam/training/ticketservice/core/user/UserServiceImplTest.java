package com.epam.training.ticketservice.core.user;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl underTest;

	private static final String USERNAME = "username";

	private static final String PASSWORD = "password";

	@Test
	void testLoginWhenUserNotFound() {

		// Given
		// When
		Mockito.when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(Optional.empty());

		// Then
		Assertions.assertEquals(Optional.empty(),underTest.login(USERNAME, PASSWORD));
		Mockito.verify(userRepository).findByUsernameAndPassword(USERNAME, PASSWORD);
	}





}
