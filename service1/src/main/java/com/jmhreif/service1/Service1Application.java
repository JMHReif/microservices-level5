package com.jmhreif.service1;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}
}

@RestController
@AllArgsConstructor
@RequestMapping("/text")
class TextController {

	@GetMapping
	String sayHello() { return "Hello, World!"; }
}

//@Data
//class Text {
//	private String someText;
//}