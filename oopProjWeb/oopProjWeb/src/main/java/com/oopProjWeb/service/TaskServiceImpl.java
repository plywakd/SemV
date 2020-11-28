package com.oopProjWeb.service;

import com.oopProjWeb.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class TaskServiceImpl implements TaskService{

    @Override
    public Optional<Task> getTask(Integer taskId) {
        return Optional.empty();
    }

    @Override
    public Task setTask(Task task) {
        return null;
    }

    @Override
    public void deleteTask(Integer taskId) {

    }

    @Override
    public Page<Task> getTasks(Pageable pageable) {
        return null;
    }
}