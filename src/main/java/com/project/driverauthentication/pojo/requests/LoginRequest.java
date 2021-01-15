package com.project.driverauthentication.pojo.requests;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequest {
    @NonNull
    private String userName;
    @NonNull
    private String password;

}
