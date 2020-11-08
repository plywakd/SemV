package com.oopProj.Controller;

import com.oopProj.Models.Project;
import com.oopProj.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api")
public class ProjectRestController {

    private ProjectService projectService;

    @Autowired
    public ProjectRestController(ProjectService projectService){
        this.projectService=projectService;
    }

    @GetMapping("projects/{project_id}")
    ResponseEntity<Project> getProject(@PathVariable Long projectId){
        return ResponseEntity.of(projectService.getProject(projectId));
    }

    @PostMapping(path = "/projects")
    ResponseEntity<Void> createProject(@Valid @RequestBody Project project){
        Project createdProject = projectService.setProject(project);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{projectId}").buildAndExpand(createdProject.getProjectId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
