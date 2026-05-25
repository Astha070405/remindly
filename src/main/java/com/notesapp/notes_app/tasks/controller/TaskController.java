package com.notesapp.notes_app.tasks.controller;

import com.notesapp.notes_app.tasks.dto.CreateTaskRequest;
import com.notesapp.notes_app.tasks.dto.TaskResponse;
import com.notesapp.notes_app.tasks.entity.Task;
import com.notesapp.notes_app.tasks.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request){
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getMyTasks(){
        return taskService.getMyTasks();
    }

    @PatchMapping("/{taskId}/complete")
    public TaskResponse completeTask(@PathVariable UUID taskId){
        return taskService.completeTask(taskId);
    }
    @DeleteMapping("/{taskId}")
    public void deleteTask(
            @PathVariable UUID taskId
    ) {

        taskService.deleteTask(taskId);
    }
}
