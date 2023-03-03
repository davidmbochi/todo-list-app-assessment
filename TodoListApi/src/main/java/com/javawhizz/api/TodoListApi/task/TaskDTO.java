package com.javawhizz.api.TodoListApi.task;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;
}
