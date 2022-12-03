package com.epam.training.ticketservice.core.movie.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieDtoTest {

	private String title = "Avatar";
	private String genre = "action";
	private int length = 120;

	@Test
	void testConstructor() {
		// Given - When
		MovieDto underTest = new MovieDto(title, genre, length);

		// Then
		Assertions.assertEquals(title, underTest.getTitle());
		Assertions.assertEquals(genre, underTest.getGenre());
		Assertions.assertEquals(length, underTest.getLength());
	}

	@Test
	void testBuilder() {
		// Given - When
		MovieDto underTest = MovieDto.builder()
				.withTitle(title)
				.withGenre(genre)
				.withLength(length)
				.build();

		// Then
		Assertions.assertEquals(title, underTest.getTitle());
		Assertions.assertEquals(genre, underTest.getGenre());
		Assertions.assertEquals(length, underTest.getLength());
	}

	@Test
	void testToString() {
		// Given - When
		MovieDto underTest = new MovieDto(title, genre, length);

		// Then
		Assertions.assertEquals("Avatar (action, 120 minutes)", underTest.toString());
	}

}
