package com.epam.training.ticketservice.core.room.persistence.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.ticketservice.core.room.persistence.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	Room findByName(String name);

	@Transactional
	Integer deleteByName(String name);
}
