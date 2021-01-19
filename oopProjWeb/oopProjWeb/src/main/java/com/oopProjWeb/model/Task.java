package com.oopProjWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    private Integer taskId;
    private Project project;
    private String name;
    private Integer taskOrder;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime addedDate;

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
