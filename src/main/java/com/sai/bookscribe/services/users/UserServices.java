package com.sai.bookscribe.services.users;

import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}