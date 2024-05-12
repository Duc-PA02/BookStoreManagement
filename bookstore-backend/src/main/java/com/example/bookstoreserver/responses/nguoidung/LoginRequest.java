package com.example.bookstoreserver.responses.nguoidung;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
