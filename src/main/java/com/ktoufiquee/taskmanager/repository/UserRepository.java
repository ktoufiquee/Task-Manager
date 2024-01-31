package com.ktoufiquee.taskmanager.repository;

import com.ktoufiquee.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> { }
