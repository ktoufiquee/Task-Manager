package com.ktoufiquee.taskmanager.auth;

import com.ktoufiquee.taskmanager.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.UUID;
@Component
public class AuthenticateToken {
    final AuthenticatedUser authenticatedUser;
    final TokenRepository tokenRepository;
    @Autowired
    public AuthenticateToken(AuthenticatedUser authenticatedUser, TokenRepository tokenRepository) {
        this.authenticatedUser = authenticatedUser;
        this.tokenRepository = tokenRepository;
    }

    public void extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            UUID uuid = UUID.fromString(token);
            String username = tokenRepository.findByToken(uuid).getUsername();
            authenticatedUser.setUsername(username);
        } else {
            authenticatedUser.setUsername(null);
        }
    }
}
