package ru.Parcifall.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;


@SpringBootApplication
public class NauJavaApplication {
	@Autowired
	private CommandProcessor commandProcessor;

	public static void main(String[] args) {
		SpringApplication.run(NauJavaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandScanner() {
		return args -> {
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("Введите команду. 'exit' для выхода.");
				while (true) {
					System.out.println("> ");
					String input = scanner.nextLine();
					if ("exit".equalsIgnoreCase(input.trim())) {
						System.out.println("Выход из программы...");
						System.exit(1);
					}

					commandProcessor.processCommand(input);
				}
			}
		};
	}
}
