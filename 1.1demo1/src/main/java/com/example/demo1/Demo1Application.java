package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@Bean
	public String message() {
		System.out.println("Message Bean is created!");
		return "Hello Spring!";
	}

	@Bean
	public String uppercaseMessage(MessageService messageService){
		System.out.println("uppercaseMessage Bean is created!");
		return messageService.uppercase();
	}

	@Bean
	public String lowercaseMessage(MessageService messageService){
		System.out.println("lowercaseMessage Bean is created!");
		return messageService.lowercase();
	}

}
