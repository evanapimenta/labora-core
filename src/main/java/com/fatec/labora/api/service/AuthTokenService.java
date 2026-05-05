package com.fatec.labora.api.service;

import com.fatec.labora.domain.*;
import com.fatec.labora.domain.enums.TokenType;
import com.fatec.labora.repository.AuthTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthTokenService {

    private final AuthTokenRepository repository;

    public AuthTokenService(AuthTokenRepository repository) {
        this.repository = repository;
    }

    public AuthToken createEmailVerificationToken(User user) {
        AuthToken token = new AuthToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setType(TokenType.VERIFY_EMAIL);
        token.setExpiresAt(LocalDateTime.now().plusHours(24));
        token.setUsed(false);

        return repository.save(token);
    }

    public AuthToken validateToken(String tokenValue) {
        AuthToken token = repository.findByToken(tokenValue)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (token.isUsed()) {
            throw new RuntimeException("Token já utilizado");
        }

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        return token;
    }

    public void markAsUsed(AuthToken token) {
        token.setUsed(true);
        repository.save(token);
    }
}