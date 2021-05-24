package com.project.driverauthentication.controllers;

import com.project.driverauthentication.pojo.requests.LoginRequest;
import com.project.driverauthentication.pojo.requests.SignUpRequest;
import com.project.driverauthentication.pojo.responses.LoginResponse;
import com.project.driverauthentication.pojo.responses.SignUpResponse;
import com.project.driverauthentication.services.DriverLoginService;
import com.project.driverauthentication.services.DriverSignUpService;
import com.project.driverauthentication.utils.ApiError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/auth/driver")
@Slf4j
public class DriverAuthenticationController {

    @Autowired
    private final DriverSignUpService signUpService;

    @Autowired
    private final DriverLoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws ApiError {
        log.info("got login request with user name {}", loginRequest.getUserName());
        return loginService.signIn(loginRequest);
    }

    @PostMapping("/signUp")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("request for creating an account with user name {}", signUpRequest.getUserName());
        return signUpService.signUp(signUpRequest);
    }

}
