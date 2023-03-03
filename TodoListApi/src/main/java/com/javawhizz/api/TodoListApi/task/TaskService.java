package com.javawhizz.api.TodoListApi.task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Long createService(TaskDTO taskDTO);

    Optional<Task> findTaskById(Long taskId);

    Task updateTask(Long taskId, TaskDTO taskDTO);

    void deleteTask(Long taskId);

    List<Task> findAllTasks();
}
