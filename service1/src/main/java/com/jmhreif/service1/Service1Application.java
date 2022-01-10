package com.jmhreif.service1;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
public class Service1Application {
	@Bean
	CommandLineRunner clr(BookRepository repo) {
		return args -> {
			repo.deleteAll()
					.thenMany(Flux.just(
							new Book("1", "The Lord of the Rings: The Return of the King", "J.R.R. Tolkien"),
							new Book("2", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling"),
							new Book("3", "Star Wars: The Truce at Bakura", "Kathy Tyers"),
							new Book("4", "The Phoenix Project", "Gene Kim/Kevin Behr/George Spafford")))
					.flatMap(repo::save)
					.subscribe();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}
}

@RestController
@AllArgsConstructor
@RequestMapping("/db")
class BookController {
	BookRepository bookRepository;

	@GetMapping("/books")
	Flux<Book> getBooks() { return bookRepository.findAll(); }
}

interface BookRepository extends ReactiveCrudRepository<Book, String> {
}

@Document
@NoArgsConstructor
@AllArgsConstructor
class Book {
	@Id
	private String bookId;
	private String title;
	private String author;
}