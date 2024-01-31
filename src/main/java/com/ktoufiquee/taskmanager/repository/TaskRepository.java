package com.ktoufiquee.taskmanager.repository;

import com.ktoufiquee.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> { }
