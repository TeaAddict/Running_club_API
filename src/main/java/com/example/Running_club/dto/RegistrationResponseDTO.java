package com.example.Running_club.dto;

import java.time.LocalDateTime;

public record RegistrationResponseDTO(
        long id,
        long userId,
        String eventName,
        LocalDateTime registrationDate
) {
}
