package com.ktoufiquee.taskmanager.controller;

import com.ktoufiquee.taskmanager.auth.AuthenticateToken;
import com.ktoufiquee.taskmanager.auth.AuthenticatedUser;
import com.ktoufiquee.taskmanager.model.Task;
import com.ktoufiquee.taskmanager.model.Token;
import com.ktoufiquee.taskmanager.model.User;
import com.ktoufiquee.taskmanager.repository.TokenRepository;
import com.ktoufiquee.taskmanager.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.springframework.util.ReflectionUtils.getField;

@RestController
public class UserController {

    final UserRepository repository;
    final TokenRepository tokenRepository;
    final AuthenticatedUser authenticatedUser;
    final AuthenticateToken authenticateToken;

    public UserController(UserRepository repository, TokenRepository tokenRepository, AuthenticatedUser authenticatedUser, AuthenticateToken authenticateToken) {
        this.repository = repository;
        this.tokenRepository = tokenRepository;
        this.authenticatedUser = authenticatedUser;
        this.authenticateToken = authenticateToken;
    }

    @GetMapping("/api/authenticate")
    public boolean getAuthentication(HttpServletRequest request) {
        authenticateToken.extractToken(request);
        return authenticatedUser.getUsername() != null;
    }

    @GetMapping("/api/username")
    public String getUsername(HttpServletRequest request) {
        authenticateToken.extractToken(request);
        return authenticatedUser.getUsername();
    }

    @DeleteMapping("/api/logout")
    public boolean logout(HttpServletRequest request) {
        authenticateToken.extractToken(request);
        Token token = tokenRepository.findByUsername(authenticatedUser.getUsername());
        tokenRepository.delete(token);
        return true;
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {

        var username = body.get("username");
        var password = body.get("password");

        if (repository.findById(username).isPresent()) {
            if (repository.findById(username).get().getPassword().equals(password)) {
                if (tokenRepository.findByUsername(username) != null) {
                    return ResponseEntity.ok(tokenRepository.findByUsername(username).getToken().toString());
                }
                else {
                    tokenRepository.deleteAll(tokenRepository.findAllByUsername(username));
                    Token token = new Token(username);
                    return ResponseEntity.ok(tokenRepository.save(token).getToken().toString());
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
    }

    @PostMapping("/api/register")
    public User createUser(@Valid @RequestBody User user) {
        return repository.save(user);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArgumentException (MethodArgumentNotValidException e) {
        var errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldname = ((FieldError) error).getField();
                    var message = error.getDefaultMessage();
                    errors.put(fieldname, message);

                    System.out.println("Validation Error - Field: " + fieldname + ", Message: " + message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}

