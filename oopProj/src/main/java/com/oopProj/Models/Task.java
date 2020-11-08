package com.oopProj.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
//    @NotBlank(message = "Field name cannot be empty")
//    @Size(min = 3, max = 50, message = "Name needs to be in <3,50> range")
    @Column(nullable = false,length = 50)
    private String name;
    private Integer order;
    @Column(length = 1000)
    private String description;
    @Column(name="datetime_add", nullable = false)
    private LocalDateTime addedDate;
}
