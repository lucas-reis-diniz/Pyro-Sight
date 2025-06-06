package com.pyro_sigth.api.dtos.auth;

import com.pyro_sigth.api.domains.user.UserRole;

public record RegisterDTO(String login, String password, String email, UserRole role) {
}
