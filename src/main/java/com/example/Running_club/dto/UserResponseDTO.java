package com.example.Running_club.dto;

import com.example.Running_club.model.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserResponseDTO(
        long id,

        String username,

        List<Role> roles
) {
}