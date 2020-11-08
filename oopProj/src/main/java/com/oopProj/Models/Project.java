package com.oopProj.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;
    @Column(name = "return_date", nullable = false)
    private LocalDate returnData;
    @OneToMany(mappedBy = "project_id")
    private List<Task> tasks;
    @ManyToMany
    @JoinTable(name = "project_student", joinColumns = {@JoinColumn(name="project_id")},
    inverseJoinColumns = {@JoinColumn(name="student_id")})
    private Set<Student> students;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getReturnData() {
        return returnData;
    }

    public void setReturnData(LocalDate returnData) {
        this.returnData = returnData;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
