package com.example.immobiliSpring.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDTO {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String roles;

    private LocalDateTime createdAt;
}
