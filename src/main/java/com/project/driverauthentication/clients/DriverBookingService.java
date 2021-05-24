package com.project.driverauthentication.clients;

import com.project.driverauthentication.pojo.requests.BookingDetailsRequest;
import com.project.driverauthentication.pojo.responses.BookingDetailsResponse;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

@Component
public interface DriverBookingService {

    @GET("/driver/booking/requestsNearBy/{vehicleType}/{pinCode}")
    Call<List<BookingDetailsResponse>> getBookings(@Path("vehicleType") String vehicleType, @Path("pinCode") Integer pinCode);

    @POST("/driver/booking/bookRide")
    Call<BookingDetailsResponse> bookRide(@Query("rideId") Integer rideId, @Query("driverId") Long driverId);

    @PUT("/driver/booking/cancelRide")
    Call<BookingDetailsResponse> cancelRide(@Query("rideId") Integer rideId, @Query("driverId") Long driverId);

    @PUT("/driver/booking/startRide")
    Call<Void> startRide(@Query("rideId") Integer rideId, @Query("driverId") Long driverId);

    @PUT("/driver/booking/endRide")
    Call<Void> endRide(@Query("rideId") Integer rideId, @Query("driverId") Long driverId);
}
