package com.example.Running_club.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RunningEventResponseDTO(
        long id,

        String name,

        LocalDate calendarDate,

        String location,

        int maxParticipants
) {
}
