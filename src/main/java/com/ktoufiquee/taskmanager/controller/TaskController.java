package com.ktoufiquee.taskmanager.controller;

import com.ktoufiquee.taskmanager.model.Task;
import com.ktoufiquee.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping("/task/{taskid}")
    public Task getTask(@PathVariable("taskid") Integer taskid) {
        return repository.findById(taskid).get();
    }
}
