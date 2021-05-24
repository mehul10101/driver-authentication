package com.project.driverauthentication.pojo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookingDetailsRequest {
    private String vehicleType;
    private Integer pinCode;

}
