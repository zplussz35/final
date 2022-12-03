package com.epam.training.ticketservice.core.screening.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Screening {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne
	private Movie movie;

	@OneToOne
	private Room room;

	private LocalDateTime startTime;

	public Screening(Movie movie, Room room, LocalDateTime startTime) {
		this.movie = movie;
		this.room = room;
		this.startTime = startTime;
	}

}
