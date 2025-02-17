package com.example.Running_club.controller;

import com.example.Running_club.dto.*;
import com.example.Running_club.model.Registration;
import com.example.Running_club.model.RunningEvent;
import com.example.Running_club.model.User;
import com.example.Running_club.service.RegistrationService;
import com.example.Running_club.service.RunningEventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RunningEventController {

  RunningEventService runningEventService;
  RegistrationService registrationService;

  public RunningEventController(RegistrationService registrationService, RunningEventService runningEventService) {
    this.registrationService = registrationService;
    this.runningEventService = runningEventService;
  }

  @PostMapping("/events")
  public ResponseEntity<?> saveEvent(@Valid @RequestBody RunningEventRequestDTO runningEventRequestDTO) {
    RunningEvent runningEvent = RunningEventMapper.toRunningEvent(runningEventRequestDTO);

    RunningEvent runningEventSaved = runningEventService.saveRunningEvent(runningEvent);

    RunningEventResponseDTO runningEventResponseDTO = RunningEventMapper.toRunningEventResponseDTO(runningEventSaved);

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(runningEventResponseDTO.id())
                            .toUri())
            .body(runningEventResponseDTO);
  }

  @DeleteMapping("/events/{eventId}")
  public ResponseEntity<?> deleteEvent(@PathVariable long eventId) {
    Optional<RunningEvent> runningEvent = runningEventService.findRunningEventById(eventId);
    if (runningEvent.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    runningEventService.deleteRunningEventById(eventId);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/events")
  public ResponseEntity<List<RunningEventResponseDTO>> getEvents() {
    List<RunningEvent> runningEvents = runningEventService.getAllRunningEvents();

    List<RunningEventResponseDTO> runningEventResponseDTOS = RunningEventMapper.toRunningEventResponseDTOS(runningEvents);

    return ResponseEntity.ok(runningEventResponseDTOS);
  }

  @GetMapping("/events/{eventId}/participants")
  public ResponseEntity<List<RegisteredUserResponseDTO>> getRegisteredUsers(@PathVariable long eventId) {
    List<Registration> registrations = registrationService.getAllEventRegistrationsById(eventId);

    List<User> users = registrations.stream().map(Registration::getUser).toList();

    Optional<RunningEvent> runningEvent = runningEventService.findRunningEventById(eventId);
    if (runningEvent.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(RegisteredUserMapper.registeredUserResponseDTOS(users));
  }

}
