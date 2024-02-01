package com.ktoufiquee.taskmanager.repository;

import com.ktoufiquee.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByUsername(String username);
}
