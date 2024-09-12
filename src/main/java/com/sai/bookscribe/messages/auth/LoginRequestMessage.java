package com.sai.bookscribe.messages.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestMessage {
    private String email;

    private String password;
}
