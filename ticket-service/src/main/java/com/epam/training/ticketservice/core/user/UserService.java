package com.epam.training.ticketservice.core.user;

import java.util.Optional;

import com.epam.training.ticketservice.core.user.model.UserDto;

public interface UserService {

	Optional<UserDto> login(String username, String password);

	Optional<UserDto> logout();

	Optional<UserDto> describe();

	void registerUser(String username, String password);
}
