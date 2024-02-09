package com.atm.dto;


import com.atm.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;


@Builder
public record CreateUserRequest(
        @NotNull
        String name,
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        @Email
        String email,
        @NotNull
        Set<Role> authorities
){
}