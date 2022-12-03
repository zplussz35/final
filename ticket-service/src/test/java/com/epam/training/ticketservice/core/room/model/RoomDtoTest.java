package com.epam.training.ticketservice.core.room.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomDtoTest {

	private String name = "Room1";
	private int rowCount = 12;
	private int columnCount = 15;

	@Test
	void testConstructor() {
		// Given - When
		RoomDto underTest = new RoomDto(name, rowCount, columnCount);

		// Then
		Assertions.assertEquals(name, underTest.getName());
		Assertions.assertEquals(rowCount, underTest.getRowCount());
		Assertions.assertEquals(columnCount, underTest.getColumnCount());
	}

	@Test
	void testBuilder() {
		// Given - When
		RoomDto underTest = RoomDto.builder()
				.withName(name)
				.withRowCount(rowCount)
				.withColumnCount(columnCount)
				.build();

		// Then
		Assertions.assertEquals(name, underTest.getName());
		Assertions.assertEquals(rowCount, underTest.getRowCount());
		Assertions.assertEquals(columnCount, underTest.getColumnCount());
	}

	@Test
	void testToString() {
		// Given - When
		RoomDto underTest = new RoomDto(name, rowCount, columnCount);

		// Then
		Assertions.assertEquals("Room Room1 with 180 seats, 12 rows and 15 columns", underTest.toString());
	}
}
