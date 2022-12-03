package com.epam.training.ticketservice.core.screening;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistence.repository.ScreeningRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

	private final ScreeningRepository screeningRepository;

	@Override
	public List<ScreeningDto> getScreeningList() {
		return screeningRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public void createScreening(ScreeningDto screeningDto) {
		Screening screening = new Screening(screeningDto.getMovie(),
				screeningDto.getRoom(),
				screeningDto.getStartTime());
		screeningRepository.save(screening);

	}

	@Override
	public Integer deleteScreening(String movieTitle, String roomName, String startTime) {
		Integer deletedId = screeningRepository.deleteByMovieTitle(movieTitle);
		Objects.requireNonNull(deletedId, "'" + movieTitle + "' titled movie screening was not found!");
		return deletedId;
	}

	private ScreeningDto convertEntityToDto(Screening screening) {
		return ScreeningDto.builder()
				.withMovie(screening.getMovie())
				.withRoom(screening.getRoom())
				.withStartTime(screening.getStartTime())
				.build();
	}

	private Optional<ScreeningDto> convertEntityToDto(Optional<Screening> screening) {
		return screening.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(screening.get()));
	}
}
