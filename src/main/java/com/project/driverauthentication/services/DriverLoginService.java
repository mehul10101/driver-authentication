package com.project.driverauthentication.services;

import com.project.driverauthentication.entities.DriverEntity;
import com.project.driverauthentication.pojo.requests.LoginRequest;
import com.project.driverauthentication.pojo.responses.LoginResponse;
import com.project.driverauthentication.repositories.DriverRepository;
import com.project.driverauthentication.security.jwt.JwtUtils;
import com.project.driverauthentication.utils.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

// The service class will contain the business logic and will talk directly to the rest end point
@Service
@Component
@Slf4j
public class DriverLoginService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;

    public LoginResponse signIn(LoginRequest loginRequest) throws ApiError {
        Optional<DriverEntity> driverEntityOptional = driverRepository.findFirstByUserName(loginRequest.getUserName());
        DriverEntity driverEntity = getUserEntityAndValidateUserNameAndPassword(loginRequest, driverEntityOptional);
        log.info("login successful");

        return getLoginResponse(loginRequest, driverEntity);

    }

    private LoginResponse getLoginResponse(LoginRequest loginRequest, DriverEntity driverEntity) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(driverEntity.getId());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtUtil.generateJwtToken(authentication);
        loginResponse.setToken(token);
        return loginResponse;
    }

    //to validate userName and password using BCrypt
    private DriverEntity getUserEntityAndValidateUserNameAndPassword(LoginRequest loginRequest, Optional<DriverEntity> driverEntityOptional) throws ApiError {
        if(!driverEntityOptional.isPresent()){
            throw new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "email not registered", "email not registered");
        }
        DriverEntity driverEntity = driverEntityOptional.get();
        if(!BCrypt.checkpw(loginRequest.getPassword(), driverEntity.getPassword())){
            throw new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "password doesn't match", "password doesn't match");
        }
        return driverEntity;
    }

}
