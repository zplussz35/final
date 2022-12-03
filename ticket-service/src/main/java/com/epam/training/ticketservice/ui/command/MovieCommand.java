package com.epam.training.ticketservice.ui.command;

import java.util.Optional;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;

import lombok.AllArgsConstructor;

@ShellComponent
@AllArgsConstructor
public class MovieCommand {

	private final MovieService movieService;

	private final UserService userService;

	@ShellMethodAvailability("isAvailable")
	@ShellMethod(key = "create movie ", value = "create new movie.")
	public MovieDto createMovie(String title, String genre, int length) {

		MovieDto movieDto = MovieDto.builder()
				.withTitle(title)
				.withGenre(genre)
				.withLength(length)
				.build();

		movieService.createMovie(movieDto);
		return movieDto;
	}

	@ShellMethodAvailability("isAvailable")
	@ShellMethod(key = "update movie ", value = "Update existing movie.")
	public MovieDto updateMovie(String title, String genre, int length) {

		MovieDto movieDto = MovieDto.builder()
				.withTitle(title)
				.withGenre(genre)
				.withLength(length)
				.build();
		movieService.updateMovie(movieDto);
		return movieDto;
	}

	@ShellMethodAvailability("isAvailable")
	@ShellMethod(key = "delete movie ", value = "Delete existing movie.")
	public String deleteMovie(String title) {
		return "Movie with id: " + movieService.deleteMovie(title) + " deleted successfully";

	}

	@ShellMethod(key = "list movies", value = "List the available movies")
	public String listMovies() {
		if (movieService.getMovieList().isEmpty()) {
			return "There are no movies at the moment";
		}
		StringBuilder result = new StringBuilder();
		movieService.getMovieList().forEach(movieDto -> result.append(movieDto).append("\n"));
		return result.toString();
	}

	private Availability isAvailable() {
		Optional<UserDto> user = userService.describe();
		return user.isPresent() && user.get().getRole() == User.Role.ADMIN
				? Availability.available()
				: Availability.unavailable("You are not an admin!");
	}
}
