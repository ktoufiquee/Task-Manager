package com.ktoufiquee.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskid;
    private Integer status;
    @Size(min = 1, max = 1024)
    private String description;
    @Size(min = 1, max = 256)
    private String title;
    private String username;

    public Task() {}

    public Task(Integer taskid, Integer status, String description, String title, String username) {
        this.taskid = taskid;
        this.status = status;
        this.description = description;
        this.title = title;
        this.username = username;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String details) {
        this.description = details;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
