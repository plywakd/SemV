package com.oopProj.service;

import com.oopProj.models.Project;
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
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepo;
    private TaskRepository taskRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepo, TaskRepository taskRepo) {
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
    }


    @Override
    public Optional<Project> getProject(Integer projectId) {
        return projectRepo.findById(projectId);
    }

    @Override
    public Project setProject(Project project) {
//        TODO fix creation date is null
        System.out.println("creation date: " +project.getCreationDate());
        Project saved = new Project(project.getName(),project.getDescription(),project.getCreationDate());
        return projectRepo.save(saved);
    }

    @Override
    @Transactional
    public void deleteProject(Integer projectId) {
        for (Task task : taskRepo.findTasksForProject(projectId)) {
            taskRepo.delete(task);
        }
        projectRepo.deleteById(projectId);
    }

    @Override
    public Page<Project> getProjects(Pageable pageable) {
        return projectRepo.findAll(pageable);
    }

    @Override
    public Page<Project> searchByName(String name, Pageable pageable) {
        return projectRepo.findByNameContainingIgnoreCase(name, pageable);
    }
}
