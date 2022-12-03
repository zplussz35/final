package com.epam.training.ticketservice.core.room.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String name;

	private int rowCount;

	private int columnCount;

	public Room(String name, int rowCount, int columnCount) {
		this.name = name;
		this.rowCount = rowCount;
		this.columnCount = columnCount;
	}

}
