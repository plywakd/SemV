package com.oopProjWeb.controller;

import javax.validation.Valid;
import com.oopProjWeb.model.Project;
import com.oopProjWeb.service.ProjectService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projectList")
    public String projectList(Model model, Pageable pageable) {
        model.addAttribute("projects", projectService.getProjects(pageable).getContent());
        return "projectList";
    }

    @GetMapping("/projectEdit")
    public String projectEdit(@RequestParam(required = false) Integer projectId, Model model) {
        if (projectId != null) {
            model.addAttribute("project", projectService.getProject(projectId).get());
        } else {
            Project project = new Project();
            model.addAttribute("project", project);
        }
        return "projectEdit";
    }

    @PostMapping(path = "/projectEdit")
    public String projectEditSave(@ModelAttribute @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "projectEdit";
        }
        try {
            project = projectService.setProject(project);
        } catch (HttpStatusCodeException e) {
            bindingResult.rejectValue(null, String.valueOf(e.getStatusCode().value()),
                    e.getStatusCode().getReasonPhrase());
            return "projectEdit";
        }
        return "redirect:/projectList";
    }

    @PostMapping(params = "cancel", path = "/projectEdit")
    public String projectEditCancel() {
        return "redirect:/projectList";
    }

    @PostMapping(params = "delete", path = "/projectEdit")
    public String projectEditDelete(@ModelAttribute Project project) {
        projectService.deleteProject(project.getProjectId());
        return "redirect:/projectList";
    }
}

