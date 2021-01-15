package com.project.driverauthentication.pojo.requests;

import lombok.Data;
import lombok.NonNull;

@Data
public class SignUpRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String userName;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String name;
    @NonNull
    private String vehicleNumber;
    @NonNull
    private String vehicleType;

}
