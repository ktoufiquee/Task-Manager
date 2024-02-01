package com.ktoufiquee.taskmanager.controller;

import com.ktoufiquee.taskmanager.auth.AuthenticateToken;
import com.ktoufiquee.taskmanager.auth.AuthenticatedUser;
import com.ktoufiquee.taskmanager.model.Task;
import com.ktoufiquee.taskmanager.repository.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private final TaskRepository repository;
    private final AuthenticateToken authenticateToken;
    private final AuthenticatedUser authenticatedUser;

    public TaskController(TaskRepository repository, AuthenticateToken authenticateToken, AuthenticatedUser authenticatedUser) {
        this.repository = repository;
        this.authenticateToken = authenticateToken;
        this.authenticatedUser = authenticatedUser;
    }

    @GetMapping("/task")
    public ResponseEntity<List<Task>> getAllTask(HttpServletRequest request) {

        authenticateToken.extractToken(request);
        var username = authenticatedUser.getUsername();
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(repository.findAllByUsername(username));
    }

    @PatchMapping("/task/{taskid}")
    public ResponseEntity<Task> patchTask(@PathVariable Integer taskid, @RequestBody Map<String, String> body, HttpServletRequest request) {

        var status = Integer.parseInt(body.get("status"));

        authenticateToken.extractToken(request);
        var username = authenticatedUser.getUsername();
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (repository.findById(taskid).isPresent()) {
            var task = repository.findById(taskid).get();
            task.setStatus(status);
            return ResponseEntity.ok(repository.save(task));
        }

        return ResponseEntity.internalServerError().body(null);
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> body, HttpServletRequest request) {

        var details = body.get("details");
        authenticateToken.extractToken(request);
        var username = authenticatedUser.getUsername();
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Task task = new Task();
        task.setStatus(0);
        task.setDetails(details);
        task.setUsername(username);
        return ResponseEntity.ok(repository.save(task));
    }
}
