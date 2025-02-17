package com.example.Running_club.controller;

import com.example.Running_club.dto.RegistrationMapper;
import com.example.Running_club.dto.RegistrationRequestDTO;
import com.example.Running_club.dto.RegistrationResponseDTO;
import com.example.Running_club.model.Registration;
import com.example.Running_club.model.RunningEvent;
import com.example.Running_club.model.User;
import com.example.Running_club.service.RegistrationService;
import com.example.Running_club.service.RunningEventService;
import com.example.Running_club.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegistrationController {

  RegistrationService registrationService;
  RunningEventService runningEventService;
  UserService userService;

  public RegistrationController(RegistrationService registrationService, RunningEventService runningEventService, UserService userService) {
    this.registrationService = registrationService;
    this.runningEventService = runningEventService;
    this.userService = userService;
  }

  @PostMapping("/events/{eventId}/register")
  public ResponseEntity<?> saveRegistration(@PathVariable long eventId,
                                            @Valid @RequestBody RegistrationRequestDTO registrationRequestDTO,
                                            Authentication authentication
  ) {
    Map<String, String> errors = new HashMap<>();
    long userIdAuth = ((User) authentication.getPrincipal()).getId();
    long userIdBody = registrationRequestDTO.user().getId();

    if (userIdAuth != userIdBody) {
      errors.put("message", "Can not register other users");
      return ResponseEntity.badRequest().body(errors);
    }

    Optional<User> user = userService.getUserById(userIdBody);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Optional<RunningEvent> runningEvent = runningEventService.findRunningEventById(eventId);
    if (runningEvent.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    boolean isRegistered = user.get().getRegistrations().stream().anyMatch(r -> r.getRunningEvent().getId() == eventId);
    if (isRegistered) {
      errors.put("message", "User is already registered to this event: " + runningEvent.get().getName());
      return ResponseEntity.badRequest().body(errors);
    }
    
    Registration registration = registrationService.saveRegistration(new Registration(
            LocalDateTime.now(),
            user.get(),
            runningEvent.get()
    ));

    RegistrationResponseDTO registrationResponseDTO = RegistrationMapper.toRegistrationResponse(registration);

    return ResponseEntity.ok(registrationResponseDTO);
  }
}
