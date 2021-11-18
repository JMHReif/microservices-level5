package com.jmhreif.service2;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
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
class TextController {
	private final WebClient client;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	Mono<String> getText() {
		return client.get()
				.uri("/text")
				.retrieve()
				.bodyToMono(String.class);
	}
}
