package com.example.rudimentryChat.controler;

import com.example.rudimentryChat.form.ChatForm;
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
    public String chat(@ModelAttribute ChatForm chatForm, Model model) {
        model.addAttribute("chatMessages", this.messageService.getMessages());
        return "chat";
    }

    @PostMapping
    public String postMessage(@ModelAttribute ChatForm chatForm, Model model){
        this.messageService.addMessage(chatForm.getChatMessage());
        model.addAttribute("chatMessages", this.messageService.getMessages());
        chatForm.setChatMessage(new ChatMessage());
        return "chat";
    }
}
