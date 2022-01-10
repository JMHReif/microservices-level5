package com.jmhreif.service2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Service2Application {

	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}

	@Bean
	WebClient client() {
		return WebClient.create("http://localhost:8080");
	}

}

@Component
@AllArgsConstructor
class BookController {
	private final WebClient client;

	Flux<Book> getBooks() {
		return client.get()
				.uri("/db/books")
				.retrieve()
				.bodyToFlux(Book.class);
	}
}

@Data
class Book {
	private String bookId;
	private String title;
	private String author;
}
