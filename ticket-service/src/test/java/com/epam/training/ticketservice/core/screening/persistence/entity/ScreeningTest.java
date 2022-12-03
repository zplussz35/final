package com.epam.training.ticketservice.core.screening.persistence.entity;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;

public class ScreeningTest {

	@Test
	void testConstructor() {
		// Given
		Movie movie = new Movie("Avatar", "action", 120);
		com.epam.training.ticketservice.core.room.persistence.entity.Room room = new com.epam.training.ticketservice.core.room.persistence.entity.Room("Room1", 12, 15);
		LocalDateTime startTime = LocalDateTime.of(2022,11,16,10,15);

		// When
		Screening underTest = new Screening(movie,room,startTime);

		// Then
		Assertions.assertEquals(movie, underTest.getMovie());
		Assertions.assertEquals(room, underTest.getRoom());
		Assertions.assertEquals(startTime, underTest.getStartTime());
	}
}
