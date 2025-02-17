package com.example.Running_club.dto;

import com.example.Running_club.model.Registration;

public class RegistrationMapper {

  public static RegistrationResponseDTO toRegistrationResponse(Registration registration) {
    return new RegistrationResponseDTO(
            registration.getId(),
            registration.getUser().getId(),
            registration.getRunningEvent().getName(),
            registration.getRegistrationDate()
    );
  }
}
