package com.epam.training.ticketservice.core.movie.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class MovieDto {

	private final String title;

	private final String genre;

	private final int length;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String title;

		private String genre;

		private int length;

		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder withGenre(String genre) {
			this.genre = genre;
			return this;
		}

		public Builder withLength(int length) {
			this.length = length;
			return this;
		}

		public MovieDto build() {
			return new MovieDto(title, genre, length);
		}
	}

	@Override
	public String toString() {
		return title + " (" + genre + ", " + length + " minutes)";
	}
}
