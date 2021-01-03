package com.oopProj.service;

import com.oopProj.models.Task;
import com.oopProj.repository.ProjectRepository;
import com.oopProj.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepo;
    private ProjectRepository projectRepo;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepo, ProjectRepository projectRepo) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
    }

    @Override
    public Optional<Task> getTask(Integer taskId) {
        return taskRepo.findById(taskId);
    }

    @Override
    public Task setTask(Task task) {
        Task saved = new Task(task.getProject(), task.getName(), task.getTaskOrder(), task.getDescription());
        return taskRepo.save(saved);
    }

    @Override
    @Transactional
    public void deleteTask(Integer taskId) {
        taskRepo.deleteById(taskId);
    }

    @Override
    public Page<Task> getTasks(Pageable pageable) {
        return taskRepo.findAll(pageable);
    }
}
