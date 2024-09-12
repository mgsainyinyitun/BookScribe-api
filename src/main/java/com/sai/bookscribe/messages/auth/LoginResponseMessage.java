package com.sai.bookscribe.messages.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseMessage {
    private String token;

    private long expiresIn;
}
