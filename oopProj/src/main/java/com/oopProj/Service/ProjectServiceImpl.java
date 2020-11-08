package com.oopProj.Service;

import com.oopProj.Models.Project;
import com.oopProj.Models.Task;
import com.oopProj.Repository.ProjectRepository;
import com.oopProj.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepo;
    private TaskRepository taskRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepo, TaskRepository taskRepo){
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
    }


    @Override
    public Optional<Project> getProject(Long projectId) {
        return projectRepo.findById(projectId);
    }

    @Override
    public Project setProject(Project project) {
        return null;
    }

    @Override
    @Transactional
    public void deleteProject(Long projectId) {
        for( Task task : taskRepo.findTasksForProject(projectId)){
            taskRepo.delete(task);
        }
        projectRepo.deleteById(projectId);
    }

    @Override
    public Page<Project> getProjects(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Project> searchByName(String name, Pageable pageable) {
        return null;
    }
}
