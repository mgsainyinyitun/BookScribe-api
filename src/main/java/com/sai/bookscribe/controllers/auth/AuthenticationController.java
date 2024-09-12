package com.sai.bookscribe.controllers.auth;


import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.auth.LoginRequestMessage;
import com.sai.bookscribe.messages.auth.LoginResponseMessage;
import com.sai.bookscribe.messages.auth.RegisterRequestMessage;
import com.sai.bookscribe.services.AuthenticationService;
import com.sai.bookscribe.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("signup")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterRequestMessage request) {
        UserEntity response = authenticationService.signup(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseMessage> authenticate(@RequestBody LoginRequestMessage request) {
        UserEntity authenticatedUser = authenticationService.authenticate(request);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseMessage response = new LoginResponseMessage();
        response.setToken(jwtToken);
        response.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(response);
    }

    @PostMapping("test")
    public String test() {


        return "hello ok!";
    }
}
