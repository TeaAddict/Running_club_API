package com.example.Running_club.controller;

import com.example.Running_club.dto.UserMapper;
import com.example.Running_club.dto.UserRequestDTO;
import com.example.Running_club.dto.UserResponseDTO;
import com.example.Running_club.model.User;
import com.example.Running_club.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/auth/register")
  public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    if (userService.existsByUsername(userRequestDTO.username())) {
      return ResponseEntity.badRequest().body("Already exists");
    }

    User user = UserMapper.toUser(userRequestDTO);
    user.setPassword(passwordEncoder.encode(userRequestDTO.password()));

    User savedUser = userService.saveUser(user);
    UserResponseDTO userResponseDTO = UserMapper.toUserResponseDTO(savedUser);

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri())
            .body(userResponseDTO);
  }
}