package com.fatec.labora.domain;

import com.fatec.labora.domain.enums.TokenType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "auth_tokens")
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    private LocalDateTime expiresAt;

    private boolean used = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public AuthToken setId(Long id) {
        this.id = id;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthToken setToken(String token) {
        this.token = token;
        return this;
    }

    public TokenType getType() {
        return type;
    }

    public AuthToken setType(TokenType type) {
        this.type = type;
        return this;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public AuthToken setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public boolean isUsed() {
        return used;
    }

    public AuthToken setUsed(boolean used) {
        this.used = used;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public AuthToken setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public User getUser() {
        return user;
    }

    public AuthToken setUser(User user) {
        this.user = user;
        return this;
    }
}