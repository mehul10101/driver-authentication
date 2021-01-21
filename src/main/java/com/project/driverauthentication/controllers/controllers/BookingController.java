package com.project.driverauthentication.controllers.controllers;

import com.project.driverauthentication.clients.DriverBookingService;
import com.project.driverauthentication.clients.serviceGenerator.DriverBookingServiceGenerator;
import com.project.driverauthentication.pojo.requests.BookingDetailsRequest;
import com.project.driverauthentication.pojo.responses.BookingDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/driver/booking")
@Slf4j
@AllArgsConstructor
public class BookingController {

    private final DriverBookingService driverBookingService = DriverBookingServiceGenerator.getService();

    /**
     * This will get all the booking which has been requested in a particular pin code.
     * @param - body bookingDetailsRequest
     * @return BookingDetailsResponse - will contain the details of all the rides available at pin code
     * @throws IOException
     */
    @GetMapping(value = "/details")
    public List<BookingDetailsResponse> getBookingDetails(@RequestBody BookingDetailsRequest bookingDetailsRequest) throws IOException {
        try {
            Response<List<BookingDetailsResponse>> bookingDetailsResponseList =
                    driverBookingService.getBookings(bookingDetailsRequest).execute();
            return bookingDetailsResponseList.body();
        } catch (Exception e) {
            log.error("error while calling service ", e);
            throw e;
        }
    }

    /**
     * This will give confirmation that ride has been booked
     * @param - rideId - to confirmation of booking
     * @return - this will contain message which will tell if the ride has been confirmed or not
     * @throws IOException
     */
    @PostMapping(value = "/bookRide")
    public BookingDetailsResponse bookRide(@Param("rideId") Integer rideId) throws IOException {
        try {
            Response<BookingDetailsResponse> bookingResponse =
                    driverBookingService.bookRide(rideId).execute();
            return bookingResponse.body();
        } catch (Exception e) {
            log.error("error while calling service ", e);
            throw e;
        }
    }

    /**
     * This will give cancelation of the ride that has been booked.
     * @param - rideId - the booked ride id
     * @return - this will contain message that ride has been ended
     * @throws IOException
     */
    @PutMapping(value = "/cancelRide")
    public BookingDetailsResponse cancelRide(@Param("rideId") Integer rideId) throws IOException {
        try {
            Response<BookingDetailsResponse> bookingResponse =
                    driverBookingService.cancelRide(rideId).execute();
            return bookingResponse.body();
        } catch (Exception e) {
            log.error("error while calling service ", e);
            throw e;
        }
    }

    /**
     * This api will change the status of the ride to IN_PROGRESS
     * @param rideId - ride which has to be started
     * @throws IOException
     */
    @PutMapping(value = "/startRide")
    public void startRide(@Param("rideId") Integer rideId) throws IOException {
        try {
            Response<Void> response = driverBookingService.startRide(rideId).execute();
            if(!response.isSuccessful()){
                throw new RuntimeException("start ride failed");
            }
        } catch (Exception e) {
            log.error("error while calling service ", e);
            throw e;
        }
    }

    /**
     * This api will change the status of the ride to COMPLETED
     * @param rideId - ride which has to be completed
     * @throws IOException
     */
    @PutMapping(value = "/endRide")
    public void endRide(@Param("rideId") Integer rideId) throws IOException {
        try {
            Response<Void> response = driverBookingService.endRide(rideId).execute();
            if(!response.isSuccessful()){
                throw new RuntimeException("end ride failed");
            }
        } catch (Exception e) {
            log.error("error while calling service ", e);
            throw e;
        }
    }


}
