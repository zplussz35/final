package com.epam.training.ticketservice.ui.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
public class CustomExitCommand implements Quit.Command {

	@ShellMethod(key = { "quit", "exit", "terminate" }, value = "Exit the application.")
	public void quit() {
		System.out.println("Exiting the Application");
		System.exit(0);
	}
}
