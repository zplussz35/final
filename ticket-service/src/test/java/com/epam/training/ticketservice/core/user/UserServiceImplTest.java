package com.epam.training.ticketservice.core.user;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl underTest;

	private static final String USERNAME = "username";

	private static final String PASSWORD = "password";

	private static final UserDto DTO = new UserDto(USERNAME, User.Role.USER);

	private static final User ENTITY = new User(USERNAME, PASSWORD, User.Role.USER);

	@Test
	void testLoginWhenUserNotFound() {

		// Given
		// When
		Mockito.when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(Optional.empty());

		// Then
		Assertions.assertEquals(Optional.empty(), underTest.login(USERNAME, PASSWORD));
		Mockito.verify(userRepository).findByUsernameAndPassword(USERNAME, PASSWORD);
	}

	@Test
	void testLoginWhenUserFound() {

		// Given
		// When
		Mockito.when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(Optional.of(ENTITY));
		UserDto userDto = underTest.login(USERNAME, PASSWORD).get();

		// Then
		Assertions.assertEquals(DTO, userDto);
		Mockito.verify(userRepository).findByUsernameAndPassword(USERNAME, PASSWORD);
	}

	@Test
	void testLogout() {

		// Given
		Mockito.when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(Optional.of(ENTITY));

		// When
		Optional<UserDto> expected = underTest.login(USERNAME, PASSWORD);
		Optional<UserDto> actual = underTest.logout();

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(userRepository).findByUsernameAndPassword(USERNAME, PASSWORD);
	}

	@Test
	void testDescribeWhenSomebodyLoggedIn() {

		// Given
		Mockito.when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(Optional.of(ENTITY));

		// When
		Optional<UserDto> expected = underTest.login(USERNAME, PASSWORD);
		Optional<UserDto> actual = underTest.describe();

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(userRepository).findByUsernameAndPassword(USERNAME, PASSWORD);
	}

	@Test
	void testDescribeWhenNobodyLoggedIn() {

		// Given
		// When
		Optional<UserDto> actual = underTest.describe();

		// Then
		Assertions.assertEquals(Optional.empty(), actual);
	}

	@Test
	void testRegisterUser() {

		// Given
		Mockito.when(userRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(Optional.of(ENTITY));
		UserDto expected = new UserDto(USERNAME, User.Role.USER);


		// When
		underTest.registerUser(USERNAME, PASSWORD);
		UserDto actual = underTest.login(USERNAME, PASSWORD).get();

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(userRepository).findByUsernameAndPassword(USERNAME, PASSWORD);
	}



}
