package com.epam.training.ticketservice.core.room;

import java.util.List;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;

public interface RoomService {

	List<RoomDto> getRoomList();

	Room findByName(String name);

	void createRoom(RoomDto roomDto);

	RoomDto updateRoom(RoomDto roomDto);

	Integer deleteRoom(String name);
}
