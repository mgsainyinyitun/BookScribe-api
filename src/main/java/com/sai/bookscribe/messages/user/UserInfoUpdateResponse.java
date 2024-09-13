package com.sai.bookscribe.messages.user;

import com.sai.bookscribe.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoUpdateResponse {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private Integer numberOfBook;

    public UserInfoUpdateResponse(UserEntity user){
        this.id = user.getId();
        this.username = user.getUsrName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.numberOfBook = user.getBooks().size();
    }
}
