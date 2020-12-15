package com.pai.STM.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String title;
    private String description;
    @Column(name="add_date")
    private LocalDateTime addDate;
    private Type type;
    private Status status;

    public Task(String title, String description, Type type, Status status) {
        this.title = title;
        this.description = description;
        this.addDate = LocalDateTime.now();
        this.type = type;
        this.status = status;
    }
}
