package com.epam.training.ticketservice.core.movie.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movie {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String title;

	private String genre;

	private int length;

	public Movie(String title, String genre, int length) {
		this.title = title;
		this.genre = genre;
		this.length = length;
	}
}
