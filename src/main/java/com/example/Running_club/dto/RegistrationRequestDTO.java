package com.example.Running_club.dto;

import com.example.Running_club.model.User;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequestDTO(
        @NotNull
        User user
) {
}
