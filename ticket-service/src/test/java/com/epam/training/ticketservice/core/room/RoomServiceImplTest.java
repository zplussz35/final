package com.epam.training.ticketservice.core.room;


import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {

	@Mock
	private RoomRepository roomRepository;

	@InjectMocks
	private RoomServiceImpl underTest;

	private static final Room ENTITY = new Room("Big room", 12, 12);

	private static final RoomDto DTO = new RoomDto("Big room", 12, 12);

	@Test
	void testGetRoomListWhenListIsEmpty() {
		// When
		List<RoomDto> emptyRoomDtoList = Collections.emptyList();
		List<Room> emptyRoomList = Collections.emptyList();
		Mockito.when(roomRepository.findAll()).thenReturn(emptyRoomList);

		//When - Then
		Assertions.assertEquals(emptyRoomDtoList, underTest.getRoomList());
		Mockito.verify(roomRepository).findAll();
	}

	@Test
	void testGetRoomListWhenThereAreRooms() {
		// When
		List<RoomDto> roomDtoList = List.of(DTO);
		List<Room> roomList = List.of(ENTITY);
		Mockito.when(roomRepository.findAll()).thenReturn(roomList);

		//When - Then
		Assertions.assertEquals(roomDtoList, underTest.getRoomList());
		Mockito.verify(roomRepository).findAll();
	}

	@Test
	void testFindByNameWhenRoomIsFound() {
		// Given
		String name = "Big room";
		Mockito.when(roomRepository.findByName(name)).thenReturn(ENTITY);

		//When - Then
		Assertions.assertEquals(ENTITY, underTest.findByName(name));
		Mockito.verify(roomRepository).findByName(name);
	}

	@Test
	void testFindByNameWhenRoomNotFound() {
		// Given
		String name = "Room 123";
		Mockito.when(roomRepository.findByName(name)).thenReturn(null);

		//When - Then
		Assertions.assertThrows(IllegalArgumentException.class, () -> underTest.findByName(name));
		Mockito.verify(roomRepository).findByName(name);
	}

	@Test
	void testDeleteRoomWhenRoomNotFound() {
		// Given
		String name = "Avatar23";
		Mockito.when(roomRepository.deleteByName(name)).thenReturn(null);

		//When - Then
		Assertions.assertThrows(NullPointerException.class, () -> underTest.deleteRoom(name));
		Mockito.verify(roomRepository).deleteByName(name);
	}

	@Test
	void testDeleteRoomWhenRoomIsFound() {
		// Given
		String name = "Big room";
		Mockito.when(roomRepository.deleteByName(name)).thenReturn(1);

		//When - Then
		Assertions.assertEquals(1, underTest.deleteRoom(name));
		Mockito.verify(roomRepository).deleteByName(name);
	}
}
