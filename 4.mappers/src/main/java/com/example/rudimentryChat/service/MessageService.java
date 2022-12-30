package com.example.rudimentryChat.service;

import com.example.rudimentryChat.pojo.ChatMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private List<ChatMessage> chatMessages;

    public void addMessage(ChatMessage chatMessage) {
        this.chatMessages.add(chatMessage);
    }

    public ArrayList<ChatMessage> getMessages(){
        return new ArrayList<>(this.chatMessages);
    }

    @PostConstruct
    public void postConstruct(){
        this.chatMessages = new ArrayList<>();
    }
}
