package com.epam.training.ticketservice.core.screening.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;

public class ScreeningDtoTest {

	private Movie movie = new Movie("Avatar", "action", 120);
	private Room room = new Room("Room1", 12, 15);
	private LocalDateTime startTime = LocalDateTime.of(2022, 11, 16, 10, 15);


	@Test
	void testConstructor() {

		// Given - When
		ScreeningDto underTest = new ScreeningDto(movie, room, startTime);

		// Then
		Assertions.assertEquals(movie, underTest.getMovie());
		Assertions.assertEquals(room, underTest.getRoom());
		Assertions.assertEquals(startTime, underTest.getStartTime());
	}

	@Test
	void testBuilder() {
		// Given - When
		ScreeningDto underTest = ScreeningDto.builder()
				.withMovie(movie)
				.withRoom(room)
				.withStartTime(startTime)
				.build();

		// Then
		Assertions.assertEquals(movie, underTest.getMovie());
		Assertions.assertEquals(room, underTest.getRoom());
		Assertions.assertEquals(startTime, underTest.getStartTime());
	}

	@Test
	void testToString() {
		// Given - When
		ScreeningDto underTest = new ScreeningDto(movie,room,startTime);

		// Then
		Assertions.assertEquals("Avatar (action, 120 minutes), screened in room Room1, at 2022-11-16 10:15", underTest.toString());
	}
}
