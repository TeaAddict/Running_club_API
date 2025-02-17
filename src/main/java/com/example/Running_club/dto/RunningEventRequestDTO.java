package com.example.Running_club.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RunningEventRequestDTO(

        @NotNull
        @Size(min = 5, message = "At least 5 characters long")
        String name,

        @NotNull
        @Future(message = "Date has to be in future")
        LocalDate calendarDate,

        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9_ ]*$", message = "Only letters and numbers")
        String location,

        @Min(1)
        int maxParticipants

) {
}
