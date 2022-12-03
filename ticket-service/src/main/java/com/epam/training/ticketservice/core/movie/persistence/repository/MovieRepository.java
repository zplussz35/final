package com.epam.training.ticketservice.core.movie.persistence.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Movie findByTitle(String title);

	@Transactional
	Integer deleteByTitle(String title);
}
