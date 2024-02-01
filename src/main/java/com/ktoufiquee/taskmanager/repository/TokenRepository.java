package com.ktoufiquee.taskmanager.repository;

import com.ktoufiquee.taskmanager.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, String> {
    Token findByUsername(String username);
    List<Token> findAllByUsername(String username);
    Token findByToken(UUID token);
}
