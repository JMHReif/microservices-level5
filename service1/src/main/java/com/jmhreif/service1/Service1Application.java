package com.jmhreif.service1;

import lombok.*;
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
		return args -> repo.deleteAll()
					.thenMany(Flux.just(
							new Book("The Lord of the Rings: The Return of the King", "J.R.R. Tolkien"),
							new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling"),
							new Book("Star Wars: The Truce at Bakura", "Kathy Tyers"),
							new Book("The Phoenix Project", "Gene Kim/Kevin Behr/George Spafford")))
					.flatMap(repo::save)
					.log()
					.subscribe();
	}

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}
}

@RestController
@AllArgsConstructor
@RequestMapping("/db")
class BookController {
	private final BookRepository bookRepository;

	@GetMapping("/books")
	Flux<Book> getBooks() { return bookRepository.findAll(); }
}

interface BookRepository extends ReactiveCrudRepository<Book, String> {
}

@Data
@Document
class Book {
	@Id
	private String bookId;
	@NonNull
	private String title;
	@NonNull
	private String author;
}