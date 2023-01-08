package com.example.rudimentryChat.pojo;

public enum MessageType {
    SAY("Say"),
    SHOUT("Shout"),
    WHISPER("Whisper");

    private final String displayValue;

    MessageType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}
