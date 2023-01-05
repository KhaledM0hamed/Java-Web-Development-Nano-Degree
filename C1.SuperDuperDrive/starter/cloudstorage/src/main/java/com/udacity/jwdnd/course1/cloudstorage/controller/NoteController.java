package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping()
    public String addNote(
            Authentication authentication,
            @ModelAttribute("noteForm") Note noteForm,
            Model model){

        String username = authentication.getName();
        User user = userService.getUser(username);
        if (noteForm.getNoteId() != null){
            int creationResult = noteService.editNote(
                    new Note(noteForm.getNoteId(),
                            noteForm.getNoteTitle(),
                            noteForm.getNoteDescription(),
                            user.getUserId()));
        } else {
            int creationResult = noteService.createNote(
                    new Note(null,
                            noteForm.getNoteTitle(),
                            noteForm.getNoteDescription(),
                            user.getUserId()));
        }

        model.addAttribute("result", "success");

        return "result";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        noteService.deleteNote(id);
        model.addAttribute("result", "success");
        return "result";
    }

}
