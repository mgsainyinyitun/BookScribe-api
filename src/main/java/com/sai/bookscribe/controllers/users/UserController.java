package com.sai.bookscribe.controllers.users;

import com.sai.bookscribe.constants.AppConstant;
import com.sai.bookscribe.controllers.auth.AuthenticationController;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.user.UserInfoRequest;
import com.sai.bookscribe.messages.user.UserInfoResponse;
import com.sai.bookscribe.messages.user.UserInfoUpdateRequest;
import com.sai.bookscribe.messages.user.UserInfoUpdateResponse;
import com.sai.bookscribe.services.users.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstant.VERSION+"/user")
@CrossOrigin(origins="*")
public class UserController {
    private  final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser(@RequestParam(required = false)UserInfoRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserEntity currentUser = (UserEntity) authentication.getPrincipal();
            return ResponseEntity.ok(new UserInfoResponse(currentUser));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getCause());
        }
    }

    @PutMapping("update")
    ResponseEntity<?> update(@RequestBody UserInfoUpdateRequest request) {
        try {
            UserInfoUpdateResponse response = userServices.update(request, AuthenticationController.getUser());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getCause());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> allUsers() {
        return ResponseEntity.ok("It is ok");
    }
}

