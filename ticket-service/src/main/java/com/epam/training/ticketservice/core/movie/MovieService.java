package com.epam.training.ticketservice.core.movie;

import java.util.List;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;

public interface MovieService {

	List<MovieDto> getMovieList();

	Movie findByTitle(String title);

	void createMovie(MovieDto movieDto);

	MovieDto updateMovie(MovieDto movieDto);

	Integer deleteMovie(String title);
}
