package com.oopProj.Service;

import com.oopProj.Models.Project;
import com.oopProj.Models.Task;
import com.oopProj.Repository.ProjectRepository;
import com.oopProj.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

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
        System.out.println("CHECK INCOMING TASK: "+ task.toString());
        Task saved = task;
        return taskRepo.save(saved);
    }

    @Override
    public void deleteTask(Integer taskId) {

    }

    @Override
    public Page<Task> getTasks(Pageable pageable) {
        return taskRepo.findAll(pageable);
    }
}
