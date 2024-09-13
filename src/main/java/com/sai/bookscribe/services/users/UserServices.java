package com.sai.bookscribe.services.users;

import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.user.UserInfoUpdateRequest;
import com.sai.bookscribe.messages.user.UserInfoUpdateResponse;
import com.sai.bookscribe.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }


    public UserInfoUpdateResponse update(UserInfoUpdateRequest request,UserEntity user){
        Optional<UserEntity> existingUserOptional = userRepository.findById(user.getId());
        if (existingUserOptional.isPresent()) {
            UserEntity existingUser = existingUserOptional.get();
            existingUser.setPhone(request.getPhone());
            userRepository.save(existingUser);
            return new UserInfoUpdateResponse(existingUser);
        } else {
            // Handle user not found scenario
            throw new RuntimeException("User not found with id: " + user.getUsrName());
        }
    }
}