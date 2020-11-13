package com.oopProj.Controller;

import com.oopProj.Models.Project;
import com.oopProj.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("projects/{project_id}")
    ResponseEntity<Project> getProject(@PathVariable Integer projectId) {
        return ResponseEntity.of(projectService.getProject(projectId));
    }

    @PostMapping(path = "/projects")
    ResponseEntity<Void> createProject(@Valid @RequestBody Project project) {
        Project createdProject = projectService.setProject(project);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{projectId}").buildAndExpand(createdProject.getProjectId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<Void> updateProject(@Valid @RequestBody Project project, @PathVariable Integer projectId) {
        return projectService.getProject(projectId).map(p -> {
            projectService.setProject(project);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/projects")
    Page<Project> getProjects(Pageable pageable) {
        return projectService.getProjects(pageable);
    }

    @GetMapping(value = "/projects", params = "name")
    Page<Project> getProjectByName(@RequestParam String name, Pageable pageable){
            return projectService.searchByName(name,pageable);
    }

}

