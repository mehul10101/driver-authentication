package com.project.driverauthentication.pojo.requests;

import lombok.Data;

@Data
public class BookingDetailsRequest {
    private String vehicleType;
    private Integer pinCode;

}
