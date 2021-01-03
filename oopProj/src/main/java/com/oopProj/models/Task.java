package com.oopProj.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(name = "task_order")
    private Integer taskOrder;
    @Column(length = 1000)
    private String description;
    @Column(name = "datetime_add", nullable = false)
    private LocalDateTime addedDate;

    public Task(Project project, String name, Integer taskOrder, String description) {
        this.project = project;
        this.name = name;
        this.taskOrder = taskOrder;
        this.description = description;
        this.addedDate = LocalDateTime.now();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(Integer taskOrder) {
        this.taskOrder = taskOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", project=" + project +
                ", name='" + name + '\'' +
                ", taskOrder=" + taskOrder +
                ", description='" + description + '\'' +
                ", addedDate=" + addedDate +
                '}';
    }
}
