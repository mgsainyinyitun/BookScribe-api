package com.sai.bookscribe.messages.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestMessage {
    private String email;

    private String password;

    private String username;

    private String phone;
}
