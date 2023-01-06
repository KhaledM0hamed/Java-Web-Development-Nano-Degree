package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String homeView(
            Authentication authentication,
            @ModelAttribute("newFile")FileForm newFile,
            Model model
            ){

        String username = authentication.getName();
        User user = userService.getUser(username);
        List<File> userFiles = fileService.getUserFilesById(user.getUserId());
        List<Note> userNotes = noteService.getUserNotes(user.getUserId());
        List<Credential> userCredential = credentialService.getUserCredentials(user);
        model.addAttribute("notes", userNotes);
        model.addAttribute("files", userFiles);
        model.addAttribute("credentials", userCredential);
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }
}
