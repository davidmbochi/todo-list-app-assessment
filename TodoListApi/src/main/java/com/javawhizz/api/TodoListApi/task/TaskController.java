package com.javawhizz.api.TodoListApi.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @PostMapping
    ResponseEntity<Long> createTask(@RequestBody TaskDTO taskDTO){
               Long taskId = taskService.createService(taskDTO);
        return new ResponseEntity<>(taskId, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    ResponseEntity<Task> findTaskById(@PathVariable("id") Long taskId){
        Task task = taskService.findTaskById(taskId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("task with id %s does not exist", taskId)));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<Task>> findAllTasks(){
        List<Task> allTasks = taskService.findAllTasks();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @PutMapping("{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") Long taskId,
                                    @RequestBody TaskDTO taskDTO){

        Task theTask = taskService.updateTask(taskId, taskDTO);
        return new ResponseEntity<>(theTask, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
