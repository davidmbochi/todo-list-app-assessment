package com.javawhizz.api.TodoListApi.task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Long createService(CreateTaskDTO createTaskDTO);

    Optional<Task> findTaskById(Long taskId);

    Task updateTask(Long taskId, UpdateTaskDTO updateTaskDTO);

    void deleteTask(Long taskId);

    List<Task> findAllTasks();
}
