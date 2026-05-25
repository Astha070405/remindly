package com.notesapp.notes_app.tasks.entity;

import com.notesapp.notes_app.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean completed;

    private boolean archived;

    private boolean reminderEnabled;

    private boolean oneDayReminderSent;

    private boolean twelveHourReminderSent;

    private boolean oneHourReminderSent;
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
