package com.notesapp.notes_app.notes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNoteRequest {
    @NotBlank
    private String title;

    private String content;
}
