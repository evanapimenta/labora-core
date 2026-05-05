package com.fatec.labora.api.controller.dto;

import java.time.Instant;
import java.util.UUID;

public class UserResponseDTO {
    private UUID userId;
    private String name;
    private String email;
    private Instant createdAt;

    public String getName() {
        return name;
    }

    public UserResponseDTO setName(String name) {
        this.name = name;
        return this;
    }

    public UUID getUserId() {
        return userId;
    }

    public UserResponseDTO setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResponseDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UserResponseDTO setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}