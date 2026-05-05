package com.fatec.labora.mapper;

import com.fatec.labora.api.controller.dto.UserResponseDTO;
import com.fatec.labora.domain.User;

public final class UserMapper {
    private UserMapper() {}

    public static UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO()
                .setUserId(user.getId())
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setCreatedAt(user.getCreatedAt());
    }
}