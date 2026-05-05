package com.fatec.labora.security;

import com.fatec.labora.domain.User;
import com.fatec.labora.domain.CustomUserDetails;
import com.fatec.labora.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener {

    private final UserRepository userRepository;

    public AuthenticationEventListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {

        Object principal = event.getAuthentication().getPrincipal();

        if (!(principal instanceof CustomUserDetails userDetails)) {
            return;
        }

        User user = userRepository.findById(userDetails.getId())
                .orElseThrow();

        user.updateLastLogin();
        userRepository.save(user);
    }
}