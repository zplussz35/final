package com.epam.training.ticketservice.core.screening.persistence.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

	Screening findByMovieTitle(String title);

	@Transactional
	Integer deleteByMovieTitle(String title);

}
