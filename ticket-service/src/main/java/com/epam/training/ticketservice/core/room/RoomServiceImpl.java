package com.epam.training.ticketservice.core.room;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;

	@Override
	public List<RoomDto> getRoomList() {
		return roomRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public Room findByName(String name) {
		Room room = roomRepository.findByName(name);
		if (room == null) {
			throw new IllegalArgumentException("Room with name: '" + name + "' not found!");
		}
		return room;
	}

	@Override
	public void createRoom(RoomDto roomDto) {
		Room room = new Room(roomDto.getName(),
				roomDto.getRowCount(),
				roomDto.getColumnCount());
		roomRepository.save(room);

	}

	@Override
	public RoomDto updateRoom(RoomDto roomDto) {
		Room roomFromDb = roomRepository.findByName(roomDto.getName());
		Objects.requireNonNull(roomFromDb, "'" + roomDto.getName() + "' room was not found!");

		roomFromDb.setName(roomDto.getName());
		roomFromDb.setRowCount(roomDto.getRowCount());
		roomFromDb.setColumnCount(roomDto.getColumnCount());

		roomRepository.save(roomFromDb);
		return convertEntityToDto(roomFromDb);
	}

	@Override
	public Integer deleteRoom(String name) {
		Integer deletedId = roomRepository.deleteByName(name);
		Objects.requireNonNull(deletedId, "'" + name + "' room was not found!");
		return deletedId;
	}

	private RoomDto convertEntityToDto(Room room) {
		return RoomDto.builder()
				.withName(room.getName())
				.withRowCount(room.getRowCount())
				.withColumnCount(room.getColumnCount())
				.build();
	}

	private Optional<RoomDto> convertEntityToDto(Optional<Room> room) {
		return room.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(room.get()));
	}
}
