package com.example.rudimentryChat.model;

import com.example.rudimentryChat.pojo.ChatMessage;
import com.example.rudimentryChat.pojo.MessageType;

public class MessageForm {

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    private MessageType messageType;

    private ChatMessage chatMessage;

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }
}
