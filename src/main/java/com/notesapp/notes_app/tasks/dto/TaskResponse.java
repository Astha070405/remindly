package com.notesapp.notes_app.tasks.dto;

import com.notesapp.notes_app.tasks.entity.Priority;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TaskResponse {
    private UUID id;

    private String title;

    private String description;

    private boolean completed;

    private boolean archived;

    private boolean reminderEnabled;

    private Priority priority;

    private LocalDateTime dueDate;
}
