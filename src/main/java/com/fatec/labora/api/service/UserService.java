package com.fatec.labora.api.service;

import com.fatec.labora.api.controller.dto.CreateUserDTO;
import com.fatec.labora.api.controller.dto.UserResponseDTO;
import com.fatec.labora.domain.User;
import com.fatec.labora.domain.enums.Role;
import com.fatec.labora.mapper.UserMapper;
import com.fatec.labora.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Transactional
    public UserResponseDTO register(CreateUserDTO dto) {
        User user = new User()
                .setEmail(dto.getEmail())
                .setPassword(passwordEncoder.encode(dto.getPassword()))
                .setName(dto.getName());

        user.addRole(Role.ROLE_USER);
        userRepository.save(user);

        emailService.sendVerificationEmail(user);
        return UserMapper.toUserResponseDTO(user);
    }
}