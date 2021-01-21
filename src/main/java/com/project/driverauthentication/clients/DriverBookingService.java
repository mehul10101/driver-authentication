package com.project.driverauthentication.clients;

import com.project.driverauthentication.pojo.requests.BookingDetailsRequest;
import com.project.driverauthentication.pojo.responses.BookingDetailsResponse;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

@Component
public interface DriverBookingService {

    @GET("/users")
    Call<List<BookingDetailsResponse>> getBookings(@Body BookingDetailsRequest bookingDetailsRequest);

    @POST
    Call<BookingDetailsResponse> bookRide(@Query("rideId") Integer rideId);

    @PUT
    Call<BookingDetailsResponse> cancelRide(@Query("rideId") Integer rideId);

    @PUT
    Call<Void> startRide(@Query("rideId") Integer rideId);

    @PUT
    Call<Void> endRide(@Query("rideId") Integer rideId);
}
