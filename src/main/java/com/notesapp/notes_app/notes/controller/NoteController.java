package com.notesapp.notes_app.notes.controller;

import com.notesapp.notes_app.notes.dto.CreateNoteRequest;
import com.notesapp.notes_app.notes.dto.NoteResponse;
import com.notesapp.notes_app.notes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public NoteResponse createNote(@Valid @RequestBody CreateNoteRequest request){
        return noteService.createNote(request);
    }
    @GetMapping
    public List<NoteResponse> getMyNotes(){
        return noteService.getMyNotes();
    }
}
