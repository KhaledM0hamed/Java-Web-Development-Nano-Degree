package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView(){
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user,
                             RedirectAttributes redirectAttributes,
                             Model model){
        String signupError = null;

        if (!userService.isUserNameAvailable(user.getUserName())){
            signupError = "username is already exist!";
        }

        if (signupError == null){
            int createUserResult = userService.createUser(user);
            if (createUserResult < 0){
                signupError = "Creating user Error, please try again";
            }
        }

        if (signupError == null){
            model.addAttribute("signupSuccess", true);
            redirectAttributes.addAttribute("isSuccess",true);
            redirectAttributes.addAttribute("signupMsg","You successfully signed up!");
            return "redirect:/login";
        }else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
