package com.example.Running_club.dto;

import com.example.Running_club.model.User;

import java.util.List;

public class RegisteredUserMapper {

  public static RegisteredUserResponseDTO toRegisteredUserResponseDTO(User user) {
    return new RegisteredUserResponseDTO(
            user.getId(),
            user.getUsername()
    );
  }

  public static List<RegisteredUserResponseDTO> registeredUserResponseDTOS(List<User> users) {
    return users.stream().map(RegisteredUserMapper::toRegisteredUserResponseDTO).toList();
  }

}
