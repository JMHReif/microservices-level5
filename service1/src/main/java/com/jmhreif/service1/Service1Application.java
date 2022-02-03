package com.jmhreif.service1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Service1Application {

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
	private String bookID;
	@NonNull
	private String title;
	@NonNull
	private String authors;
}