package com.jmhreif.service3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Service3Application {

	public static void main(String[] args) {
		SpringApplication.run(Service3Application.class, args);
	}

}

@RestController
@AllArgsConstructor
@RequestMapping("/db")
class AuthorController {
	private final AuthorRepository authorRepo;

	@GetMapping
	String liveCheck() { return "Service3 is up"; }

	@GetMapping("/author/{id}")
	Mono<Author> getAuthor(@PathVariable String id) { return authorRepo.findById(id); }

	@GetMapping("/authors")
	Flux<Author> getAuthors() { return authorRepo.findAll(); }
}

interface AuthorRepository extends ReactiveCrudRepository<Author, String> {
}

@Data
@Document
class Author {
	@Id
	private String id;
	@NonNull
	private String author_id;

	private String name, average_rating, ratings_count, text_reviews_count;
}
