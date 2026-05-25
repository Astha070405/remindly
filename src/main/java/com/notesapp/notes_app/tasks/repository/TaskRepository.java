package com.notesapp.notes_app.tasks.repository;

import com.notesapp.notes_app.tasks.entity.Task;
import com.notesapp.notes_app.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByUser(User user);
}
