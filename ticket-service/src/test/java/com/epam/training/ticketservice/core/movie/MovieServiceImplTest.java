package com.epam.training.ticketservice.core.movie;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

	@Mock
	private MovieRepository movieRepository;

	@InjectMocks
	private MovieServiceImpl underTest;

	private static final Movie ENTITY = new Movie("Avatar", "action", 120);

	private static final MovieDto DTO = new MovieDto("Avatar", "action", 120);

	@Test
	void testGetMovieListWhenListIsEmpty() {
		// When
		List<MovieDto> emptyMovieDtoList = Collections.emptyList();
		List<Movie> emptyMovieList = Collections.emptyList();
		Mockito.when(movieRepository.findAll()).thenReturn(emptyMovieList);

		//When - Then
		Assertions.assertEquals(emptyMovieDtoList, underTest.getMovieList());
		Mockito.verify(movieRepository).findAll();
	}

	@Test
	void testGetMovieListWhenThereAreMovies() {
		// When
		List<MovieDto> movieDtoList = List.of(DTO);
		List<Movie> movieList = List.of(ENTITY);
		Mockito.when(movieRepository.findAll()).thenReturn(movieList);

		//When - Then
		Assertions.assertEquals(movieDtoList, underTest.getMovieList());
		Mockito.verify(movieRepository).findAll();
	}

	@Test
	void testFindByTitleWhenMovieIsFound() {
		// Given
		String title = "Avatar";
		Mockito.when(movieRepository.findByTitle(title)).thenReturn(ENTITY);

		//When - Then
		Assertions.assertEquals(ENTITY, underTest.findByTitle(title));
		Mockito.verify(movieRepository).findByTitle(title);
	}

	@Test
	void testFindByTitleWhenMovieNotFound() {
		// Given
		String title = "Avatar";
		Mockito.when(movieRepository.findByTitle(title)).thenReturn(null);

		//When - Then
		Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.findByTitle(title));
		Mockito.verify(movieRepository).findByTitle(title);
	}

	@Test
	void testDeleteMovieWhenMovieNotFound() {
		// Given
		String title = "Avatar23";
		Mockito.when(movieRepository.deleteByTitle(title)).thenReturn(null);

		//When - Then
		Assertions.assertThrows(NullPointerException.class, () -> underTest.deleteMovie(title));
		Mockito.verify(movieRepository).deleteByTitle(title);
	}
}
