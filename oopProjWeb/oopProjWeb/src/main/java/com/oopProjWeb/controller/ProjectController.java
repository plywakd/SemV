package com.oopProjWeb.controller;

import javax.validation.Valid;

import com.oopProjWeb.model.Project;
import com.oopProjWeb.model.Student;
import com.oopProjWeb.model.Task;
import com.oopProjWeb.service.ProjectService;
import com.oopProjWeb.service.StudentService;
import com.oopProjWeb.service.TaskService;
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
    private StudentService studentService;
    private TaskService taskService;

    public ProjectController(ProjectService projectService, StudentService studentService, TaskService taskService) {
        this.projectService = projectService;
        this.studentService = studentService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String mainMenu(){
        return "mainmenu";
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

    @GetMapping("/studentList")
    public String studentList(Model model, Pageable pageable) {
        model.addAttribute("students", studentService.getStudents(pageable).getContent());
        return "studentList";
    }

    @GetMapping("/studentEdit")
    public String studentEdit(@RequestParam(required = false) Integer studentId, Model model) {
        if (studentId != null) {
            model.addAttribute("student", studentService.getStudent(studentId).get());
        } else {
            Student student = new Student();
            model.addAttribute("student", student);
        }
        return "studentEdit";
    }

    @PostMapping(path = "/studentEdit")
    public String studentEditSave(@ModelAttribute @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "studentEdit";
        }
        try {
            student = studentService.setStudent(student);
        } catch (HttpStatusCodeException e) {
            bindingResult.rejectValue(null, String.valueOf(e.getStatusCode().value()),
                    e.getStatusCode().getReasonPhrase());
            return "studentEdit";
        }
        return "redirect:/studentList";
    }

    @PostMapping(params = "cancel", path = "/studentEdit")
    public String studentEditCancel() {
        return "redirect:/studentList";
    }

    @PostMapping(params = "delete", path = "/studentEdit")
    public String studentEditDelete(@ModelAttribute Student student) {
        studentService.deleteStudent(student.getStudentId());
        return "redirect:/studentList";
    }

    @GetMapping("/taskList")
    public String taskList(Model model, Pageable pageable) {
        model.addAttribute("tasks", taskService.getTasks(pageable).getContent());
        return "taskList";
    }

    @GetMapping("/taskEdit")
    public String taskEdit(@RequestParam(required = false) Integer taskId, Model model) {
        if (taskId != null) {
            Task task = taskService.getTask(taskId).get();
            model.addAttribute("task", task);
        } else {
            Task task = new Task();
            model.addAttribute("task", task);
        }
        return "taskEdit";
    }

    @PostMapping(path = "/taskEdit")
    public String taskEditSave(@ModelAttribute @Valid Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "taskEdit";
        }
        try {
            task = taskService.setTask(task);
        } catch (HttpStatusCodeException e) {
            bindingResult.rejectValue(null, String.valueOf(e.getStatusCode().value()),
                    e.getStatusCode().getReasonPhrase());
            return "taskEdit";
        }
        return "redirect:/taskList";
    }

    @PostMapping(params = "cancel", path = "/taskEdit")
    public String taskEditCancel() {
        return "redirect:/taskList";
    }

    @PostMapping(params = "delete", path = "/taskEdit")
    public String taskEditDelete(@ModelAttribute Task task) {
        taskService.deleteTask(task.getTaskId());
        return "redirect:/taskList";
    }
}

