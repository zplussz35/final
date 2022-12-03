package com.epam.training.ticketservice.core.room.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class RoomDto {

	private final String name;

	private final int rowCount;

	private final int columnCount;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String name;

		private int rowCount;

		private int columnCount;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withRowCount(int rowCount) {
			this.rowCount = rowCount;
			return this;
		}

		public Builder withColumnCount(int columnCount) {
			this.columnCount = columnCount;
			return this;
		}

		public RoomDto build() {
			return new RoomDto(name, rowCount, columnCount);
		}
	}

	@Override
	public String toString() {
		return "Room " + name + " with " + (rowCount * columnCount) +
				" seats, " + rowCount +
				" rows and " + columnCount + " columns";
	}
}
