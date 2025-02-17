package com.example.Running_club.dto;

import com.example.Running_club.model.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public record UserRequestDTO(
        @NotNull
        @Pattern(regexp = "^[a-z0-9]+$", message = "Must be lower cased letters")
        @Size(min = 4, max = 100, message = "Min 4, Max 100 characters")
        String username,

        @NotNull
        @Size(min = 6, max = 150, message = "Min 6, Max 150 characters")
        String password,

        @NotNull(message = "Roles are required")
        List<Role> roles
) {
}