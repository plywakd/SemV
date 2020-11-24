package com.oopProj.Service;

import com.oopProj.Models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskService {

    Optional<Task> getTask(Integer taskId);
    Task setTask(Task task);
    void deleteTask(Integer taskId);
    Page<Task> getTasks(Pageable pageable);
}
