package com.epam.training.ticketservice.core.movie;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;

	@Override
	public List<MovieDto> getMovieList() {
		return movieRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public Movie findByTitle(String title) {
		Movie movie = movieRepository.findByTitle(title);
		if (movie == null) {
			throw new IllegalArgumentException("Movie with title: '" + title + "' not found!");
		}
		return movie;
	}

	@Override
	public void createMovie(MovieDto movieDto) {
		Movie movie = new Movie(movieDto.getTitle(),
				movieDto.getGenre(),
				movieDto.getLength());
		movieRepository.save(movie);
	}

	@Override
	public MovieDto updateMovie(MovieDto movieDto) {
		Movie movieFromDb = movieRepository.findByTitle(movieDto.getTitle());
		Objects.requireNonNull(movieFromDb, "'" + movieDto.getTitle() + "' movie was not found!");

		movieFromDb.setTitle(movieDto.getTitle());
		movieFromDb.setGenre(movieDto.getGenre());
		movieFromDb.setLength(movieDto.getLength());

		movieRepository.save(movieFromDb);
		return convertEntityToDto(movieFromDb);
	}

	@Override
	public Integer deleteMovie(String title) {
		Integer deletedId = movieRepository.deleteByTitle(title);
		Objects.requireNonNull(deletedId, "'" + title + "' movie was not found!");
		return deletedId;
	}

	private MovieDto convertEntityToDto(Movie movie) {
		return MovieDto.builder()
				.withTitle(movie.getTitle())
				.withGenre(movie.getGenre())
				.withLength(movie.getLength())
				.build();
	}

	private Optional<MovieDto> convertEntityToDto(Optional<Movie> movie) {
		return movie.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(movie.get()));
	}
}
