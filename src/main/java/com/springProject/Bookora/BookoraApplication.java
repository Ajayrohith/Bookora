package com.springProject.Bookora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class BookoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookoraApplication.class, args);
	}

}
