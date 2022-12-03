package com.epam.training.ticketservice.core.movie.persistence.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovieTest {

	@Test
	void testConstructor() {
		// Given
		String title = "Avatar";
		String genre = "action";
		int length = 120;

		// When
		Movie underTest = new Movie(title, genre, length);

		// Then
		Assertions.assertEquals(title, underTest.getTitle());
		Assertions.assertEquals(genre, underTest.getGenre());
		Assertions.assertEquals(length, underTest.getLength());
	}
}
