package com.example.Running_club.dto;


import com.example.Running_club.model.Role;
import com.example.Running_club.model.User;

import java.util.List;

public class UserMapper {

  public static User toUser(UserRequestDTO userRequestDTO) {
    return new User(
            userRequestDTO.username(),
            userRequestDTO.password(),
            List.of(),
            userRequestDTO.roles()
    );
  }

  public static UserResponseDTO toUserResponseDTO(User user) {
    return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getRoles()
    );
  }

  public static List<UserResponseDTO> toUserResponseDTOS(List<User> users) {
    return users.stream().map(UserMapper::toUserResponseDTO).toList();
  }

}