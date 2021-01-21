package com.project.driverauthentication.pojo.responses;

import lombok.Data;

@Data
public class BookingDetailsResponse {
    private Integer id;
    private Integer distanceInMeters;
    private String phoneNumber;
    private String startPoint;
    private String response;

}
