package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final UserService userService;
    private final NoteMapper noteMapper;

    public NoteService(UserService userService, NoteMapper noteMapper) {
        this.userService = userService;
        this.noteMapper = noteMapper;
    }

    public Note getNoteById(int noteId){
        return noteMapper.getNoteById(noteId);
    }

    public List<Note> getUserNotes(int noteId){
        return noteMapper.getUserNotesById(noteId);
    }

    public int createNote(Note note){
        return noteMapper.addNote(note);
    }

    public int editNote(Note note){
        return noteMapper.updateNote(note);
    }
    public int deleteNote(int noteId){
        return noteMapper.deleteNote(noteId);
    }



}
