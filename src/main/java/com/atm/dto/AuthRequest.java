package com.atm.dto;

public record AuthRequest (
        String username,
        String password
){
}
