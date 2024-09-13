package com.sai.bookscribe.messages.auth;

import com.sai.bookscribe.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterResponseMessage {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String token;

    public RegisterResponseMessage(UserEntity user){
        this.id = user.getId();
        this.username = user.getUsrName();
        this.email  = user.getEmail();
        this.phone = user.getPhone();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
