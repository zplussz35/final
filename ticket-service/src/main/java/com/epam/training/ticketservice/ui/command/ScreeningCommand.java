package com.epam.training.ticketservice.ui.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;

import lombok.AllArgsConstructor;

@ShellComponent
@AllArgsConstructor
public class ScreeningCommand {

	private final ScreeningService screeningService;

	private final MovieService movieService;

	private final RoomService roomService;

	private final UserService userService;

	@ShellMethod(key = "list screenings", value = "List the available screenings")
	public String listMovies() {
		if (screeningService.getScreeningList().isEmpty()) {
			return "There are no screenings";
		}
		StringBuilder result = new StringBuilder();
		screeningService.getScreeningList().forEach(screeningDto -> result.append(screeningDto).append("\n"));
		return result.toString();
	}

	@ShellMethodAvailability("isAvailable")
	@ShellMethod(key = "create screening ", value = "Create new screening.")
	public ScreeningDto createScreening(String movieTitle, String roomName, String startDate, String time) {

		Movie movie = movieService.findByTitle(movieTitle);
		Room room = roomService.findByName(roomName);

		String startTime = startDate + " " + time;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(startTime, formatter);

		ScreeningDto screeningDto = ScreeningDto.builder()
				.withMovie(movie)
				.withRoom(room)
				.withStartTime(dateTime)
				.build();

		checkIfScreeningIsOverlapping(screeningDto);
		
		screeningService.createScreening(screeningDto);
		return screeningDto;
	}

	@ShellMethodAvailability("isAvailable")
	@ShellMethod(key = "delete screening", value = "Delete screening.")
	public Integer deleteScreening(String movieTitle, String roomName, String startTime) {
		return screeningService.deleteScreening(movieTitle, roomName, startTime);
	}

	private Availability isAvailable() {
		Optional<UserDto> user = userService.describe();
		return user.isPresent() && user.get().getRole() == User.Role.ADMIN
				? Availability.available()
				: Availability.unavailable("You are not an admin!");
	}

	private void checkIfScreeningIsOverlapping(ScreeningDto screeningDto) {
		LocalDateTime startTime = screeningDto.getStartTime();
		int movieLength = screeningDto.getMovie().getLength();
		LocalDateTime endTime = screeningDto.getStartTime().plusMinutes(movieLength);
		String roomName = screeningDto.getRoom().getName();
		for (ScreeningDto actualScreeningDto : screeningService.getScreeningList()) {
			String actualRoomName = actualScreeningDto.getRoom().getName();
			if (roomName.equals(actualRoomName)) {
				checkActualScreeningIsOverlapping(actualScreeningDto, startTime, endTime);
			}
		}
	}

	private void checkActualScreeningIsOverlapping(ScreeningDto actualScreeningDto,
			LocalDateTime startTime,
			LocalDateTime endTime) {
		int actualMovieLength = actualScreeningDto.getMovie().getLength();
		LocalDateTime actualStartTime = actualScreeningDto.getStartTime();
		LocalDateTime actualEndTime = actualStartTime.plusMinutes(actualMovieLength);
		LocalDateTime actualEndTimePlusBreak = actualEndTime.plusMinutes(10);

		if ((startTime.isAfter(actualStartTime) && startTime.isBefore(actualEndTime)) ||
				(endTime.isAfter(actualStartTime) && endTime.isBefore(actualEndTime) ||
						(startTime.isBefore(actualStartTime) && endTime.isAfter(actualEndTime)))
		) {
			throw new IllegalArgumentException("There is an overlapping screening!");
		} else if (startTime.isAfter(actualEndTime) && startTime.isBefore(actualEndTimePlusBreak)) {
			throw new IllegalArgumentException(
					"This would start in the break period after another screening in this room");
		}

	}

}
