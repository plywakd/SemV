package com.oopProj.controller;

import com.oopProj.models.Task;
import com.oopProj.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api")
public class TaskRestController {

    private TaskService taskService;

    @Autowired
    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(path = "/tasks")
    ResponseEntity<Void> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.setTask(task);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{taskId}").buildAndExpand(createdTask.getTaskId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
