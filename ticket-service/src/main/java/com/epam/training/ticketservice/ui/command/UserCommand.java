package com.epam.training.ticketservice.ui.command;

import java.util.Optional;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;

import lombok.AllArgsConstructor;

@ShellComponent
@AllArgsConstructor
public class UserCommand {

	private final UserService userService;

	@ShellMethod(key = "sign in privileged", value = "Admin user login.")
	public String adminLogin(String username, String password) {
		Optional<UserDto> user = userService.login(username, password);
		if (user.isEmpty()) {
			return "Login failed due to incorrect credentials";
		}
		return user.get() + " is successfully logged in!";
	}

	@ShellMethod(key = "sign out", value = "Logging out from application")
	public String logout() {
		Optional<UserDto> user = userService.logout();
		if (user.isEmpty()) {
			return "You need to login first!";
		}
		return user.get() + " is logged out!";
	}

	@ShellMethod(key = "describe account", value = "Get Administrator information")
	public String describe() {
		Optional<UserDto> user = userService.describe();
		if (user.isEmpty()) {
			return "You are not signed in";
		}
		return "Signed in with privileged account '" + user.get().getUsername() + "'";
	}
}
