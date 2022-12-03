package com.epam.training.ticketservice.core.room.persistence.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomTest {

	@Test
	void testConstructor() {
		// Given
		String name = "Room1";
		int rowCount = 12;
		int columnCount = 15;

		// When
		Room underTest = new Room(name, rowCount, columnCount);

		// Then
		Assertions.assertEquals(name, underTest.getName());
		Assertions.assertEquals(rowCount, underTest.getRowCount());
		Assertions.assertEquals(columnCount, underTest.getColumnCount());
	}
}
