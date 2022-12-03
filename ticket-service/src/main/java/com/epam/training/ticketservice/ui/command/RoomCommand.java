package com.epam.training.ticketservice.ui.command;

import java.util.Optional;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;

import lombok.AllArgsConstructor;

@ShellComponent
@AllArgsConstructor
public class RoomCommand {

	private final RoomService roomService;

	private final UserService userService;

	@ShellMethodAvailability("isAvailable")
	@ShellMethod(key = "create room ", value = "create new room.")
	public RoomDto createRoom(String name, int rowCount, int columnCount) {
		RoomDto roomDto = RoomDto.builder()
				.withName(name)
				.withRowCount(rowCount)
				.withColumnCount(columnCount)
				.build();
		roomService.createRoom(roomDto);
		return roomDto;
	}

	@ShellMethodAvailability("isAvailable")
	@ShellMethod(key = "update room ", value = "Update existing room.")
	public RoomDto updateRoom(String name, int rowCount, int columnCount) {
		RoomDto roomDto = RoomDto.builder()
				.withName(name)
				.withRowCount(rowCount)
				.withColumnCount(columnCount)
				.build();
		roomService.updateRoom(roomDto);
		return roomDto;
	}

	@ShellMethodAvailability("isAvailable")
	@ShellMethod(key = "delete room ", value = "Delete existing room.")
	public Integer deleteRoom(String name) {
		return roomService.deleteRoom(name);

	}

	@ShellMethod(key = "list rooms", value = "List the available rooms")
	public String listMovies() {
		if (roomService.getRoomList().isEmpty()) {
			return "There are no rooms at the moment";
		}
		StringBuilder result = new StringBuilder();
		roomService.getRoomList().forEach(roomDto -> result.append(roomDto).append("\n"));
		return result.toString();
	}

	private Availability isAvailable() {
		Optional<UserDto> user = userService.describe();
		return user.isPresent() && user.get().getRole() == User.Role.ADMIN
				? Availability.available()
				: Availability.unavailable("You are not an admin!");
	}
}
