package com.epam.training.ticketservice.core.screening;

import java.util.List;

import com.epam.training.ticketservice.core.screening.model.ScreeningDto;

public interface ScreeningService {

	List<ScreeningDto> getScreeningList();

	void createScreening(ScreeningDto screeningDto);

	Integer deleteScreening(String movieTitle, String roomName, String startTime);
}
