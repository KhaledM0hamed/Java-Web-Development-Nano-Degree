package com.example.mvc_bascis.controller;

import com.example.mvc_bascis.pojo.MessageForm;
import com.example.mvc_bascis.service.MessageListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;

@Controller
public class HomeController {

    private MessageListService messageListService;

    public HomeController(MessageListService messageListService) {
        this.messageListService = messageListService;
    }

    @GetMapping("/home")
    public String getHomePage(@ModelAttribute("newMessage") MessageForm newMessage,
                              Model model) {

        model.addAttribute("greetings", this.messageListService.getMessages());
        return "home";
    }

    @PostMapping("/home")
    public String addMessage(@ModelAttribute("newMessage") MessageForm newMessage,
                             Model model) {

        messageListService.addMessage(newMessage.getText());
        model.addAttribute("greetings", messageListService.getMessages());
        newMessage.setText("");
        return "home";
    }

}
