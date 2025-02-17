package com.example.Running_club.dto;

import com.example.Running_club.model.RunningEvent;

import java.util.List;

public class RunningEventMapper {

  public static RunningEvent toRunningEvent(RunningEventRequestDTO runningEventRequestDTO) {
    return new RunningEvent(
            runningEventRequestDTO.name(),
            runningEventRequestDTO.calendarDate(),
            runningEventRequestDTO.location(),
            runningEventRequestDTO.maxParticipants()
    );
  }

  public static RunningEventResponseDTO toRunningEventResponseDTO(RunningEvent runningEvent) {
    return new RunningEventResponseDTO(
            runningEvent.getId(),
            runningEvent.getName(),
            runningEvent.getCalendarDate(),
            runningEvent.getLocation(),
            runningEvent.getMaxParticipants()
    );
  }

  public static List<RunningEventResponseDTO> toRunningEventResponseDTOS(List<RunningEvent> runningEvents) {
    return runningEvents.stream().map(RunningEventMapper::toRunningEventResponseDTO).toList();
  }

}