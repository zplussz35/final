package com.epam.training.ticketservice.core.configuration;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.epam.training.ticketservice.core.user.persistence.entity.User;
import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class InMemoryDatabaseInitializer {

	private final UserRepository userRepository;

	@PostConstruct
	public void init() {
		User admin = new User("admin", "admin", User.Role.ADMIN);
		userRepository.save(admin);
	}
}
