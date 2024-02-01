package com.ktoufiquee.taskmanager.controller;

import com.ktoufiquee.taskmanager.model.User;
import com.ktoufiquee.taskmanager.repository.UserRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldNotInsertForMail() {
        User user = new User("ktouf", "invalid", "pass@12A");
        userController.createUser(user);
        assertFalse(repository.findById(user.getUsername()).isPresent());
    }

    @Test
    public void shouldNotInsertForPassword() {
        User user = new User("ktouf", "valid@gmail.com", "badpass");
        userController.createUser(user);
        assertFalse(repository.findById(user.getUsername()).isPresent());
    }

}