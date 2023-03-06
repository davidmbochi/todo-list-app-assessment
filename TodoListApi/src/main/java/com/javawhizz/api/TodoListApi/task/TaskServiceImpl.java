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
    public Long createService(CreateTaskDTO createTaskDTO) {
        Task task = Task.builder()
                .title(createTaskDTO.getTitle())
                .description(createTaskDTO.getDescription())
                .dueDate(createTaskDTO.getDueDate())
                .status(TaskStatus.PENDING)
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
    public Task updateTask(Long taskId, UpdateTaskDTO updateTaskDTO) {
        return taskRepository.findById(taskId)
                .map(theTask -> {
                    theTask.setTitle(updateTaskDTO.getTitle());
                    theTask.setDescription(updateTaskDTO.getDescription());
                    theTask.setDueDate(updateTaskDTO.getDueDate());
                    theTask.setStatus(updateTaskDTO.getStatus());
                    return taskRepository.save(theTask);
                }).orElseThrow(()-> new RuntimeException(
                        String.format("The customer with id %s does not exist", taskId)));

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
