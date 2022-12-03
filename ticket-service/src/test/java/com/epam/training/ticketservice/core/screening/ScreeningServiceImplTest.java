package com.epam.training.ticketservice.core.screening;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistence.repository.ScreeningRepository;

@ExtendWith(MockitoExtension.class)
public class ScreeningServiceImplTest {

	@Mock
	private ScreeningRepository screeningRepository;

	@InjectMocks
	private ScreeningServiceImpl underTest;


	private static final Movie MOVIE_ENTITY = new Movie("Avatar", "action", 120);
	private static final Room ROOM_ENTITY = new Room("Big room", 12, 12);

	private static final LocalDateTime START_TIME = LocalDateTime.of(2022,11,16,12,30);

	private static final Screening SCREENING_ENTITY = new Screening(MOVIE_ENTITY, ROOM_ENTITY, START_TIME);


	private static final ScreeningDto SCREENING_DTO = new ScreeningDto(MOVIE_ENTITY, ROOM_ENTITY, START_TIME);

	@Test
	void testGetScreeningListWhenListIsEmpty() {
		// When
		List<ScreeningDto> emptyScreeningDtoList = Collections.emptyList();
		List<Screening> emptyRoomList = Collections.emptyList();
		Mockito.when(screeningRepository.findAll()).thenReturn(emptyRoomList);

		//When - Then
		Assertions.assertEquals(emptyScreeningDtoList, underTest.getScreeningList());
		Mockito.verify(screeningRepository).findAll();
	}

	@Test
	void testGetScreenListWhenThereAreRooms() {
		// When
		List<ScreeningDto> screeningDtoList = List.of(SCREENING_DTO);
		List<Screening> screeningList = List.of(SCREENING_ENTITY);
		Mockito.when(screeningRepository.findAll()).thenReturn(screeningList);

		//When - Then
		Assertions.assertEquals(screeningDtoList, underTest.getScreeningList());
		Mockito.verify(screeningRepository).findAll();
	}

	@Test
	void testDeleteRoomWhenRoomNotFound() {
		// Given
		String name = "Avatar23";
		String roomName = SCREENING_ENTITY.getRoom().getName();
		Mockito.when(screeningRepository.deleteByMovieTitle(name)).thenReturn(null);

		//When - Then
		Assertions.assertThrows(NullPointerException.class, () -> underTest.deleteScreening(name,roomName,START_TIME.toString()));
		Mockito.verify(screeningRepository).deleteByMovieTitle(name);
	}
}
