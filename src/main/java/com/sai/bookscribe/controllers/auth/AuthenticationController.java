package com.sai.bookscribe.controllers.auth;


import com.sai.bookscribe.constants.AppConstant;
import com.sai.bookscribe.entities.UserEntity;
import com.sai.bookscribe.messages.auth.LoginRequestMessage;
import com.sai.bookscribe.messages.auth.LoginResponseMessage;
import com.sai.bookscribe.messages.auth.RegisterRequestMessage;
import com.sai.bookscribe.messages.auth.RegisterResponseMessage;
import com.sai.bookscribe.repositories.UserRepository;
import com.sai.bookscribe.services.auths.AuthenticationService;
import com.sai.bookscribe.services.auths.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstant.VERSION+"/auth")
@CrossOrigin(origins="*")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequestMessage request) {

        try {
            // Register User
            UserEntity usr = authenticationService.signup(request);

            // Try Login
            LoginRequestMessage req = new LoginRequestMessage();
            req.setEmail(request.getEmail());
            req.setPassword(request.getPassword());
            UserEntity authenticatedUser = authenticationService.authenticate(req);
            String jwtToken = jwtService.generateToken(authenticatedUser);

            // Response
            RegisterResponseMessage response = new RegisterResponseMessage(usr);
            response.setToken(jwtToken);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getCause());
        }
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseMessage> authenticate(@RequestBody LoginRequestMessage request) {
        UserEntity authenticatedUser = authenticationService.authenticate(request);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseMessage response = new LoginResponseMessage();
        response.setToken(jwtToken);
        response.setExpiresIn(jwtService.getExpirationTime());

        response.setUsername(authenticatedUser.getUsrName());

        return ResponseEntity.ok(response);
    }

    @PostMapping("test")
    public String test() {
        return "hello ok!";
    }

    @PostMapping("validate")
    public String validate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity usr = getUser();
        return usr.getUsrName();
    }

    public static UserEntity getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) authentication.getPrincipal();
    }
}
