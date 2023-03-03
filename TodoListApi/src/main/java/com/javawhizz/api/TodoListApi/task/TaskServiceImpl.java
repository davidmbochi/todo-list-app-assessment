package com.javawhizz.api.TodoListApi.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Override
    public Long createService(TaskDTO taskDTO) {
        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .dueDate(taskDTO.getDueDate())
                .status(taskDTO.getStatus())
                .build();
        if (taskRepository.findTaskByTitle(task.getTitle()).isPresent()){
            throw new RuntimeException(String.format("task with title %s is present", task.getTitle()));
        }
        return taskRepository.save(task).getTaskId();
    }

    @Override
    public Optional<Task> findTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task updateTask(Long taskId, TaskDTO taskDTO) {
        Task theTask = taskRepository.findById(taskId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("task with id %s does not exist", taskId)));
        theTask.setTitle(taskDTO.getTitle());
        theTask.setDescription(taskDTO.getDescription());
        theTask.setStatus(taskDTO.getStatus());
        theTask.setDueDate(taskDTO.getDueDate());
        return taskRepository.save(theTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        if (task == null){
            throw new RuntimeException(String.format("The customer with id %s does not exist", taskId));
        }
        taskRepository.delete(task);
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}
