package com.javawhizz.api.TodoListApi.task;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CreateTaskDTO {
    private String title;
    private String description;
    private LocalDate dueDate;

}
