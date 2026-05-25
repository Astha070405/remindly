package com.notesapp.notes_app.tasks.service;

import com.notesapp.notes_app.tasks.dto.CreateTaskRequest;
import com.notesapp.notes_app.tasks.dto.TaskResponse;
import com.notesapp.notes_app.tasks.entity.Task;
import com.notesapp.notes_app.tasks.repository.TaskRepository;
import com.notesapp.notes_app.users.entity.User;
import com.notesapp.notes_app.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private TaskResponse mapToResponse;
    private final UserRepository userRepository;

    public TaskResponse createTask(
            CreateTaskRequest request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        Task task = Task.builder()

                .title(request.getTitle())

                .description(request.getDescription())

                .priority(request.getPriority())

                .dueDate(request.getDueDate())

                .reminderEnabled(
                        request.isReminderEnabled()
                )

                .completed(false)

                .archived(false)

                .createdAt(LocalDateTime.now())

                .updatedAt(LocalDateTime.now())

                .user(user)

                .build();

        Task savedTask =
                taskRepository.save(task);

        return mapToResponse(savedTask);
    }
    public void deleteTask(
            UUID taskId
    ) {

        taskRepository.deleteById(taskId);
    }
    public List<TaskResponse> getMyTasks() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return taskRepository
                .findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TaskResponse completeTask(
            UUID taskId
    ) {

        Task task =
                taskRepository
                        .findById(taskId)
                        .orElseThrow();

        task.setCompleted(true);

        task.setUpdatedAt(LocalDateTime.now());

        Task updatedTask =
                taskRepository.save(task);

        return mapToResponse(updatedTask);
    }

    private TaskResponse mapToResponse(
            Task task
    ) {

        return TaskResponse.builder()

                .id(task.getId())

                .title(task.getTitle())

                .description(task.getDescription())

                .completed(task.isCompleted())

                .archived(task.isArchived())

                .reminderEnabled(
                        task.isReminderEnabled()
                )

                .priority(task.getPriority())

                .dueDate(task.getDueDate())

                .build();
    }

}