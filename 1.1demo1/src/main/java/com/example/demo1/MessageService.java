package com.example.demo1;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private String message;

    public MessageService(String message) {
        this.message = message;
    }

    public String uppercase() {
        return message.toUpperCase();
    }

    public String lowercase() {
        return message.toLowerCase();
    }

    @PostConstruct
    public void postConstruct() { // GOOD PRACTICE
        System.out.println("Creating MessageService bean");
    }
}
