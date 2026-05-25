package com.notesapp.notes_app.tasks.dto;

import com.notesapp.notes_app.tasks.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class CreateTaskRequest {
    @NotBlank
    private String title;

    private String description;

    private Priority priority;

    private LocalDateTime dueDate;

    private boolean reminderEnabled;


}
