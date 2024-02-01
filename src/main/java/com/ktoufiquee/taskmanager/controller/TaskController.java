package com.ktoufiquee.taskmanager.controller;

import com.ktoufiquee.taskmanager.model.Task;
import com.ktoufiquee.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/task/{taskid}")
    public Task getTask(@PathVariable("taskid") Integer taskid) {
        return repository.findById(taskid).get();
    }

    @GetMapping("/task/user/{username}")
    public List<Task> getAllTask(@PathVariable("username") String username) {
        return repository.findAllByUsername(username);
    }

    @PostMapping("/task")
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }
}
