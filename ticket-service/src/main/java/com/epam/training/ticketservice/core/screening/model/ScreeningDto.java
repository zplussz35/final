package com.epam.training.ticketservice.core.screening.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class ScreeningDto {

	private final Movie movie;

	private final Room room;

	private final LocalDateTime startTime;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Movie movie;

		private Room room;

		private LocalDateTime startTime;

		public Builder withMovie(Movie movie) {
			this.movie = movie;
			return this;
		}

		public Builder withRoom(Room room) {
			this.room = room;
			return this;
		}

		public Builder withStartTime(LocalDateTime startTime) {
			this.startTime = startTime;
			return this;
		}

		public ScreeningDto build() {
			return new ScreeningDto(movie, room, startTime);
		}
	}

	@Override
	public String toString() {
		LocalDate startDate = LocalDate.of(startTime.getYear(),startTime.getMonth(),startTime.getDayOfMonth());
		LocalTime time = LocalTime.of(startTime.getHour(),startTime.getMinute());

		return movie.getTitle() +
				" (" + movie.getGenre() +
				", " + movie.getLength() + "), screened in room " + room.getName() + ", at " + startDate + " " + time;
	}
}
