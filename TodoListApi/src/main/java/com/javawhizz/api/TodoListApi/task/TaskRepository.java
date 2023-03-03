package com.javawhizz.api.TodoListApi.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
           select t from Task t where t.title = ?1
           """)
    Optional<Task> findTaskByTitle(String title);
}
