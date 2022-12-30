package com.example.rudimentryChat.controler;

import com.example.rudimentryChat.model.MessageForm;
import com.example.rudimentryChat.pojo.ChatMessage;
import com.example.rudimentryChat.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String chat(@ModelAttribute MessageForm messageForm, Model model) {
        model.addAttribute("chatMessages", this.messageService.getMessages());
        return "chat";
    }

    @PostMapping
    public String postMessage(@ModelAttribute MessageForm messageForm, Model model){
        this.messageService.addMessage(messageForm.getChatMessage());
        model.addAttribute("chatMessages", this.messageService.getMessages());
        messageForm.setChatMessage(new ChatMessage());
        return "chat";
    }
}
