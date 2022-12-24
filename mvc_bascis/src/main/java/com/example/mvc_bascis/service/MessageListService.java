package com.example.mvc_bascis.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListService {
    private List<String> messages;

    public void addMessage(String message) {
        messages.add(message);
    }

    public ArrayList<String> getMessages() {
        return new ArrayList<>(this.messages);
    }

    @PostConstruct
    public void postConstruct() {
        this.messages = new ArrayList<>();
    }
}
